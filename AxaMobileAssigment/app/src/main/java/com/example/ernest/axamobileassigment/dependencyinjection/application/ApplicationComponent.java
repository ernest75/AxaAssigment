package com.example.ernest.axamobileassigment.dependencyinjection.application;



import com.example.ernest.axamobileassigment.dependencyinjection.presentation.PresentationComponent;
import com.example.ernest.axamobileassigment.dependencyinjection.presentation.PresentationModule;
import com.example.ernest.axamobileassigment.dependencyinjection.service.ServiceComponent;
import com.example.ernest.axamobileassigment.dependencyinjection.service.ServiceModule;

import javax.inject.Singleton;//ojo

import dagger.Component;

@Component(modules = {ApplicationModule.class, NetworkingModule.class})
public interface ApplicationComponent {

    public PresentationComponent newPresentationComponent(PresentationModule presentationModule);
    public ServiceComponent newServiceComponent(ServiceModule serviceModule);

}
