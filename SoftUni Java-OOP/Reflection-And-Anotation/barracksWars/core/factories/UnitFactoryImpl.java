package barracksWars.core.factories;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import barracksWars.interfaces.Unit;
import barracksWars.interfaces.UnitFactory;

public class UnitFactoryImpl implements UnitFactory {

    private static final String UNITS_PACKAGE_NAME =
            "barracksWars.models.units.";

    @Override
    public Unit createUnit(String unitType) {
        Unit newUnit = null;

        try {
            Class unitClass = Class.forName(UNITS_PACKAGE_NAME +  unitType);
            Constructor<Unit> unitConstr = unitClass.getConstructor();
            newUnit = unitConstr.newInstance();

        } catch (ClassNotFoundException | InvocationTargetException |
                 NoSuchMethodException | InstantiationException |
                 IllegalAccessException e) {
            e.printStackTrace();
        }

        return newUnit;
    }
}
