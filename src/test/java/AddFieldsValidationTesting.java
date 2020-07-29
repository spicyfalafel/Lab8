import com.itmo.collection.*;
import com.itmo.utils.FieldsValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class AddFieldsValidationTesting {

    static FieldsValidator validator;
    static DragonWithStringFields d;
    @BeforeAll
    static void start(){
        validator = new FieldsValidator();
        d = new DragonWithStringFields();
        String name = "5";
        String x = "5";
        String y = "5";
        String wingspan = "5";
        String age = "5";
        DragonType type = null;
        DragonCharacter character = DragonCharacter.GOOD;
        String killerName = "";
        Country nationality = null;
        Color hairColor = null;
        String date = "";
        String locName = "";
        String killerX = "";
        String killerY = "";
        String killerZ = "";

        d.setName(name);
        d.setX(x);
        d.setY(y);
        d.setWingspan(wingspan);
        d.setAge(age);
        d.setKillerName(killerName);
        d.setBirthday(date);
        d.setLocName(locName);
        d.setKillerX(killerX);
        d.setKillerY(killerY);
        d.setKilllerZ(killerZ);
        d.setType(type);
        d.setCharacter(character);
        d.setHairColor(hairColor);
        d.setNationality(nationality);
    }

    @Test
    void dIsFine(){
        Assertions.assertTrue(validator.dragonFieldsAreAllFine(d));
    }
}
