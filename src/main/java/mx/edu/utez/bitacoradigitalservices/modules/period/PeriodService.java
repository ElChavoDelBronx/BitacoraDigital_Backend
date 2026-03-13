package mx.edu.utez.bitacoradigitalservices.modules.period;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeriodService {

    private final PeriodRepository repository;

    public PeriodService(PeriodRepository repository) {
        this.repository = repository;
    }

    public List<Period> findAll() {
        return repository.findAll();
    }

    public Period findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Period save(Period period) {
        return repository.save(period);
    }

    public Period update(Long id, Period period) {
        Period existing = repository.findById(id).orElse(null);

        if (existing != null) {
            existing.setNamePeriod(period.getNamePeriod());
            existing.setStartDate(period.getStartDate());
            existing.setDueDate(period.getDueDate());
            existing.setState(period.getState());

            return repository.save(existing);
        }

        return null;
    }

    public boolean delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}