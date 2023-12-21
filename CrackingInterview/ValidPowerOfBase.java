package CrackingInterview;

import java.util.function.BiFunction;

public class ValidPowerOfBase implements BiFunction<Integer, Integer, Boolean> {

    @Override
    public Boolean apply(Integer number, Integer base) {
        
        return isValidPowerOfBase(number, base);
    }

    private Boolean isValidPowerOfBase(Integer number, Integer base) {
       if(number == 0) {
           return true;
       }
       while(base != 0) {
           if(base%number != 0) {
               return false;
           }
           base=base/number;
       }
        return true;
    }

}
