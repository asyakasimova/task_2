import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class testDemoQaForm {
    @BeforeAll
    static void setup() {
        Configuration.startMaximized = true;
    }


    @Test
    void testForm() {
        String  firstName = "Иван",
                lastName = "Иванов",
                email = "79472784992@mail.com",
                mobile ="9472784992",
                currentAddress = "Пенза, Рахманинова 156",
                state = "Uttar Pradesh",
                city = "Merrut";


        open("https://demoqa.com/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));

        $("#firstName").val(firstName);
        $("#lastName").val(lastName);
        $("#userEmail").val(email);
        $("#genterWrapper").$(byText("Other")).click();
        $("#userNumber").val(mobile);

        //dateOfBirth
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("January");
        $(".react-datepicker__year-select").selectOption("1940");
        $(".react-datepicker__day--008").click();

        //$("#subjectsContainer").click();
        $("#subjectsInput").setValue("a");
        $(byText("Arts")).click();


        //hobbies
        $(byText("Reading")).click();
        $(byText("Sports")).click();

        //picture
        $("#uploadPicture").uploadFile(new File("/Users/alex/IdeaProjects/task/src/test/java/picture.png"));

        //Current Address
        $("#currentAddress").val(currentAddress);

        //Scroll
        $("#state").scrollTo();

        //State and City
        $("#state").click(ClickOptions.usingDefaultMethod());
        $("#react-select-3-option-1").click();
        $("#city").click(ClickOptions.usingDefaultMethod());
        $("#react-select-4-option-2").click();

        //Submit and check
        $("#submit").click();
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));

        //check
        $x("//td[text()='Student Name']").parent().shouldHave(text(firstName + " " + lastName));
        $x("//td[text()='Student Email']").parent().shouldHave(text(email));
        $x("//td[text()='Gender']").parent().shouldHave(text("Other"));
        $x("//td[text()='Mobile']").parent().shouldHave(text(mobile));
        $x("//td[text()='Date of Birth']").parent().shouldHave(text("08" + " " + "January" + "," + "1940"));
        $x("//td[text()='Subjects']").parent().shouldHave(text("Arts"));
        $x("//td[text()='Hobbies']").parent().shouldHave(text("Reading" + ", " + "Sports"));
        $x("//td[text()='Picture']").parent().shouldHave(text("picture.png"));
        $x("//td[text()='Address']").parent().shouldHave(text(currentAddress));
        $x("//td[text()='State and City']").parent().shouldHave(text(state + " " + city));

        $("#closeLargeModal").click();
    }
}
