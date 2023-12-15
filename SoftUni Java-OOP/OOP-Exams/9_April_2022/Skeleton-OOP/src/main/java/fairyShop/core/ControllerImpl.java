package fairyShop.core;


import fairyShop.common.ConstantMessages;
import fairyShop.common.ExceptionMessages;
import fairyShop.models.*;
import fairyShop.repositories.HelperRepository;
import fairyShop.repositories.PresentRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


public class ControllerImpl implements Controller {
    private HelperRepository helperRepository;
    private PresentRepository presentRepository;
    private ShopImpl shop;

    public ControllerImpl() {
        this.helperRepository = new HelperRepository();
        this.presentRepository = new PresentRepository();
        this.shop = new ShopImpl();
    }

    @Override
    public String addHelper(String type, String helperName) {
        Helper helper;
        switch (type) {
            case "Happy":
                helper = new Happy(helperName);
                break;
            case "Sleepy":
                helper = new Sleepy(helperName);
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.HELPER_TYPE_DOESNT_EXIST);
        }
        this.helperRepository.add(helper);
        return String.format(ConstantMessages.ADDED_HELPER, type, helperName);
    }

    @Override
    public String addInstrumentToHelper(String helperName, int power) {
        Helper helper = this.helperRepository.findByName(helperName);
        if (helper == null) {
            throw new IllegalArgumentException(ExceptionMessages.HELPER_DOESNT_EXIST);
        }
        Instrument instrument = new InstrumentImpl(power);
        helper.addInstrument(instrument);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_INSTRUMENT_TO_HELPER, power, helperName);
    }

    @Override
    public String addPresent(String presentName, int energyRequired) {
        Present present = new PresentImpl(presentName, energyRequired);
        this.presentRepository.add(present);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_PRESENT, presentName);
    }

    @Override
    public String craftPresent(String presentName) {
        List<Helper> helpers = getHelpersForWork();
        if (helpers.isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.NO_HELPER_READY);
        }

        Present present = this.presentRepository.findByName(presentName);
        int brokenInstruments = 0;

        for (Helper helper : helpers) {
            this.shop.craft(present, helper);
            brokenInstruments += (int) helper.getInstruments()
                    .stream()
                    .filter(Instrument::isBroken).count();
            if (present.isDone()) {
                break;
            }
        }
        if (present.isDone()) {
            return String.format(ConstantMessages.PRESENT_DONE, presentName, "done") +
                    String.format(ConstantMessages.COUNT_BROKEN_INSTRUMENTS, brokenInstruments);
        }

        return String.format(ConstantMessages.PRESENT_DONE, presentName, "not done") +
                String.format(ConstantMessages.COUNT_BROKEN_INSTRUMENTS, brokenInstruments);
    }

    public List<Helper> getHelpersForWork() {
        Collection<Helper> helpers = helperRepository.getModels();
        return helpers.stream().filter(h -> h.getEnergy() > 50).collect(Collectors.toList());
    }

    @Override
    public String report() {
        StringBuilder sb = new StringBuilder();
        int countCrafted = (int) this.presentRepository.getModels()
                .stream()
                .filter(Present::isDone).count();
        sb.append(countCrafted).append(" presents are done!").append(System.lineSeparator());
        sb.append("Helpers info:").append(System.lineSeparator());
        for (Object helper : this.helperRepository.getModels()) {
            sb.append(helperInfo((Helper) helper)).append(System.lineSeparator());
        }
        return sb.toString().trim();

    }

    public static String helperInfo(Helper helper) {
        return "Name: " + helper.getName() + System.lineSeparator()
                + "Energy: " + helper.getEnergy() + System.lineSeparator()
                + "Instruments: " + (int) helper.getInstruments().stream().filter(i -> !i.isBroken()).count()
                + " not broken left";
    }
}
