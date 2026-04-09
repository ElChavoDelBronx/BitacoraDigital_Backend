package mx.edu.utez.bitacoradigitalservices.modules.period.utils;

import mx.edu.utez.bitacoradigitalservices.modules.period.Period;
import mx.edu.utez.bitacoradigitalservices.modules.period.dtos.SummaryPeriodDTO;

import java.util.ArrayList;
import java.util.List;

public class PeriodUtils {
    public static List<SummaryPeriodDTO> entityListToSummaryDTOList(List<Period> list) {
        List<SummaryPeriodDTO> dtoList = new ArrayList<>();
        for (Period p: list) {
            SummaryPeriodDTO dto = new SummaryPeriodDTO(p);
            dtoList.add(dto);
        }
        return dtoList;
    }
}
