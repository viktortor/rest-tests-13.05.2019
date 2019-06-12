package can;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.TestData;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.Collection;

@RunWith(SerenityParameterizedRunner.class)
public class DataProviderExample {
//
//
//        @TestData
//        public static Collection<Object[]> testData(){
//            return Arrays.asList(new Object[][]{
//                    {0,     Bronze},
//                    {9999,  Bronze},
//                    {10000, Silver},
//                    {49999, Silver},
//                    {50000, Gold}
//            });
//        }
//
//        private int kilometersTravelled;
//        private String expectedStatus;
//
//        public DataProviderExample1(int kilometersTravelled, String expectedStatus) {
//            this.kilometersTravelled = kilometersTravelled;
//            this.expectedStatus = expectedStatus;
//        }
//
//        @Steps
//        TravellerStatusSteps travellerSteps;
//
//        @Test
//        public void shouldEarnNextStatusWithEnoughPoints() {
//            // GIVEN
//            travellerSteps.a_traveller_joins_the_frequent_flyer_program();
//
//            // WHEN
//            travellerSteps.the_traveller_flies(kilometersTravelled);
//
//            // THEN
//            travellerSteps.traveller_should_have_a_status_of(expectedStatus);
//        }


}
