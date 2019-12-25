package ru.otus.homework7.atms;

import java.util.ArrayList;
import java.util.List;

public class ATMHelper {
    private static List<ATM> atms = new ArrayList<>();
    private static ATMGeneralInfoFactory atmGeneralInfoFactory = new ATMGeneralInfoFactory();
    private static ATMGroup atmGroup1 = new ATMGroup();
    private static ATMGroup atmGroup2 = new ATMGroup();

    public static List<ATM> getBankomats() {
        createGroupATM();
        return atms;
    }

    //что-то похожее на Composite
    private static void createGroupATM() {
        atmGroup1.addATM(atmGeneralInfoFactory.createATM(1000, 50));
        atmGroup1.addATM(atmGeneralInfoFactory.createATM(1500, 150));
        atmGroup2.addATM(atmGeneralInfoFactory.createATM(1200, 100));
        atmGroup2.addATM(atmGroup1);
        atms.addAll(atmGroup2.getATMGroup());
    }
}
