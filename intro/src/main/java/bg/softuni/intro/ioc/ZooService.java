package bg.softuni.intro.ioc;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ZooService {

    private final Animal animal1;
    private final Animal animal2;

    public ZooService(@Qualifier("mySuperDog") Animal animal1,
                      @Qualifier("cat") Animal animal2) {
        this.animal1 = animal1;
        this.animal2 = animal2;
    }

    void doWork(){
        animal1.makeNoise ();
        animal2.makeNoise ();
    }
}
