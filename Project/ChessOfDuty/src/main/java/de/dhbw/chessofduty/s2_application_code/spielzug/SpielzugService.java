package de.dhbw.chessofduty.s2_application_code.spielzug;

import de.dhbw.chessofduty.s3_domain_code.spielzug.SpielzugRepository;

public class SpielzugService {

    private SpielzugRepository spielzugRepository;

    public SpielzugService(SpielzugRepository spielzugRepository) {
        this.spielzugRepository =spielzugRepository;
    }
}
