
package IsotopicMightyGamers;

/**
 *
 * @author Poindexter
 */
public class Necessities {
    
    boolean passwordDigit(String pass)
    {
        int count = 0;
        for(int i = 0; i < pass.length(); i++)
        {
            if(pass.charAt(i) >= '0' && pass.charAt(i) <= '9')
                count ++;
        }
        
        if(count <= 0)
        {
            return false;
        }
        else
            return true;
    }
    
    
    boolean findingAt(String email)
    {
        int count = 0;
        for(int i = 0; i < email.length(); i++)
        {
            if(email.charAt(i) == '@')
            {
                count++;
            }
        } 
        if(count == 0)
            return false;
        else
            return true;
    }
    
    boolean findingDot(String email)
    {
        int count = 0;
        for(int i = 0; i < email.length(); i++)
        {
            if(email.charAt(i) == '.')
            {
                count++;
            }
        } 
        if(count == 0)
            return false;
        else
            return true;
    }
    
}
