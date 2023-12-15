package robotService;

import robotService.core.Engine;
import robotService.core.EngineImpl;
import robotService.entities.services.BaseService;

public class Main {
    public static void main(String[] args) {

        Engine engine = new EngineImpl();
        engine.run();
    }
}
