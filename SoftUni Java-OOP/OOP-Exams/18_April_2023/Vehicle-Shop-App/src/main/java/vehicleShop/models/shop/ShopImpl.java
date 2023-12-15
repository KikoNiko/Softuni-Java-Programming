package vehicleShop.models.shop;

import vehicleShop.models.tool.Tool;
import vehicleShop.models.vehicle.Vehicle;
import vehicleShop.models.worker.Worker;

public class ShopImpl implements Shop {
    @Override
    public void make(Vehicle vehicle, Worker worker) {
        if (worker.canWork()) {
            for (Tool tool : worker.getTools()) {
                while (!tool.isUnfit()) {
                    vehicle.making();
                    worker.working();
                    tool.decreasesPower();

                    if (!worker.canWork()) return;
                    if (vehicle.reached()) return;
                }
            }
        }
    }

    //⦁	The worker starts making the vehicle.
    // This is only possible if the worker has strength and a tool that isn't broken.
    //⦁	Keep working until the vehicle is done or the worker has strength (and tools to use).
    //⦁	If at some point the power of the current tool reaches or drops below 0,
    // meaning it is broken, then the worker should take the next tool from its collection, if it has any left.
}
