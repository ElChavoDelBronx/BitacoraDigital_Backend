package mx.edu.utez.bitacoradigitalservices.modules.projects;

import mx.edu.utez.bitacoradigitalservices.kernel.ApiResponse;
import mx.edu.utez.bitacoradigitalservices.modules.period.Period;
import mx.edu.utez.bitacoradigitalservices.modules.period.PeriodRepository;
import mx.edu.utez.bitacoradigitalservices.modules.period.dtos.BasicPeriodProjection;
import mx.edu.utez.bitacoradigitalservices.modules.projects.dtos.SaveProjectDTO;
import mx.edu.utez.bitacoradigitalservices.modules.projects.dtos.SaveProjectFormDTO;
import mx.edu.utez.bitacoradigitalservices.modules.projects.utils.ProjectUtils;
import mx.edu.utez.bitacoradigitalservices.modules.users.User;
import mx.edu.utez.bitacoradigitalservices.modules.users.UserRepository;
import mx.edu.utez.bitacoradigitalservices.modules.users.dtos.BasicUserProjection;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final PeriodRepository periodRepository;
    public ProjectService(
            ProjectRepository projectRepository,
            UserRepository userRepository,
            PeriodRepository periodRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.periodRepository = periodRepository;
    }
    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> findAllProjects() {
        ApiResponse response = new ApiResponse(
                "Proyectos obtenidos exitosamente.",
                projectRepository.findProjectSummary(),
                HttpStatus.OK
        );
        return new ResponseEntity<>(response, response.getStatus());
    }
    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> findProjectsByAdvisor(Long advisorId) {
        ApiResponse response = new ApiResponse(
                "Proyectos obtenidos exitosamente.",
                projectRepository.findProjectSummaryByAdvisor(advisorId),
                HttpStatus.OK
        );
        return new ResponseEntity<>(response, response.getStatus());
    }
    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> findProjectById(Long id) {
        ApiResponse response;
        Project found = projectRepository.findById(id).orElse(null);
        if(found != null) {
            response = new ApiResponse(
                    "Proyecto obtenido exitosamente.",
                    ProjectUtils.entityToBasicDTO(found),
                    HttpStatus.OK
            );
        } else {
            response = new ApiResponse(
                    "Recurso no encontrado.",
                    true,
                    HttpStatus.NOT_FOUND
            );
        }
        return new ResponseEntity<>(response, response.getStatus());
    }
    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> findSavingFormData() {
        ApiResponse response;
        List<BasicUserProjection> availableStudents = userRepository.findAvailableStudents();
        List<BasicUserProjection> advisors = userRepository.findAllByRol("Asesor");
        List<BasicPeriodProjection> periods = periodRepository.findActiveOrFuturePeriod();

        response = new ApiResponse(
                "Información encontrada con éxito",
                new SaveProjectFormDTO(periods, availableStudents, advisors),
                HttpStatus.OK
        );
        return new ResponseEntity<>(response, response.getStatus());
    }
    private ApiResponse validateProject(SaveProjectDTO dto) {
        Period period = periodRepository.getReferenceById(dto.idPeriod());
        Project alreadySaved = projectRepository.findExistingProject(dto.projectName(), dto.idPeriod());
        if (alreadySaved != null && !Objects.equals(alreadySaved.getId(), dto.id())) {
            return new ApiResponse(
                    "Proyecto ya registrado para ese periodo.",
                    true,
                    HttpStatus.BAD_REQUEST
            );
        } else {
            User adviser = userRepository.getReferenceById(dto.idAdviser());
            List<User> validStudents = userRepository.findAllByIdInAndRole(
                    dto.studentIds().stream().distinct().toList(), "Estudiante"
            );
            if(adviser.getRol().equals("Asesor") && validStudents.size() == dto.studentIds().size()) {
                Project project = new Project();
                if(dto.id() != null) project.setId(dto.id());
                project.setDescription(dto.description());
                project.setNameProject(dto.projectName());
                project.setPeriod(period);
                project.setAdviser(adviser);
                project.setStudents(validStudents);
                project.setNeededHours(dto.neededHours());
                return new ApiResponse(
                        "Proyecto validado correctamente.",
                        project,
                        HttpStatus.OK
                );
            } else {
                return new ApiResponse(
                        "Asesor o estudiantes seleccionados incorrectos.",
                        true,
                        HttpStatus.BAD_REQUEST
                );
            }
        }
    }
    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public ResponseEntity<ApiResponse> saveProject(SaveProjectDTO dto) {
        ApiResponse response;
        try {
            ApiResponse tempResponse = validateProject(dto);
            if(tempResponse.getStatus().equals(HttpStatus.OK)) {
                Project project = (Project) tempResponse.getData();
                projectRepository.save(project);
                response = new ApiResponse(
                        "Proyecto creado correctamente.",
                        HttpStatus.CREATED
                );
            } else {
                response = tempResponse;
            }
        } catch (Exception e) {
            response = new ApiResponse(
                    "Error Interno del Servidor",
                    true,
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
        return new ResponseEntity<>(response, response.getStatus());
    }
    @Transactional(rollbackFor = {SQLException.class, Exception.class})
    public ResponseEntity<ApiResponse> updateProject(SaveProjectDTO dto) {
        ApiResponse response;
        try {
            Project existing = projectRepository.findById(dto.id()).orElse(null);
            if (existing != null) {
                ApiResponse tempResponse = validateProject(dto);
                if(tempResponse.getStatus().equals(HttpStatus.OK)) {
                    Project project = (Project) tempResponse.getData();
                    projectRepository.save(project);
                    response = new ApiResponse(
                            "Proyecto actualizado correctamente.",
                            tempResponse.getStatus()
                    );
                } else {
                    response = tempResponse;
                }
            } else {
                response = new ApiResponse(
                        "Proyecto no encontrado.",
                        true,
                        HttpStatus.NOT_FOUND
                );
            }

        } catch (Exception e) {
            response = new ApiResponse(
                    "Error interno del servidor.",
                    true,
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
        return new ResponseEntity<>(response, response.getStatus());
    }
}
