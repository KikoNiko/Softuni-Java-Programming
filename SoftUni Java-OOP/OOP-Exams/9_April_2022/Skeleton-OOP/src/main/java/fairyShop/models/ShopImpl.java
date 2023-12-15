package fairyShop.models;

public class ShopImpl implements Shop {
    @Override
    public void craft(Present present, Helper helper) {
        if (helper.canWork()) {
            for (Instrument instrument : helper.getInstruments()) {
                while (!instrument.isBroken()) {
                    present.getCrafted();
                    helper.work();
                    instrument.use();

                    if (present.isDone()) return;
                    if (!helper.canWork()) return;
                }
            }
        }
    }

    //⦁	The helper starts crafting the present.
    // This is only possible if the helper has energy and an instrument that isn't broken.
    //⦁	Keep working until the present is done or the helper has energy (and instruments to use).
    //⦁	If at some point the power of the current instrument reaches or drops below 0, meaning it is broken,
    // then the helper should take the next instrument from its collection, if it has any left.
}
