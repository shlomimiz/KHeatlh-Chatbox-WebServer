package modules;

import com.google.inject.AbstractModule;

public class ApplicationLoader extends AbstractModule {

    @Override
    protected void configure() {

        try {
            bind(GraphLoader.class).toConstructor(GraphLoader.class.getConstructor()).asEagerSingleton();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}

