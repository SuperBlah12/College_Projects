//[2018-04-18] This was one of my early Community College programs. My professor told me to keep this for my portfolio someday, so here it is. 
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
/*
 * 
 * 
 * Well, it took a while but I think I've finnally gotten back into my programming groove.
 * I missed it so...
 * I had the general framework of the program done in a jiffy, it was the exception catching that took the most work.
 * first issue was not acccepting area codes that wern't on the list. That was waht if-else #1 was for.
 * Next was not allowing more than 3 numbers. this was taken care of by if-else #2.
 * The third issue was a toughie, not allowing anything BUT digits to be accepted. I found a nifty method called "TryParse" which appears to be on
 * any object made of numbers (Int64, Int 32, short, double etc.). Put in your string and what you want it parsed into, if it works you get your number. 
 * If not, it will output false to the attached bool and you can act accordingly. I used this also for parsing the minutes. Find these at #3.
 * Making arrays, Searching the array, finding values in parallel arrays, those were all easy. But fun! I can't wait to use them, AND TryParse more in the future.
 * 
 * Also, at the time of writing, i've figured out the if-else issues I had last time. 
 * 
 * if(1)
 *  block1;
 * else if(2)
 *  block2;
 * else if(3)
 *  block3;
 * else
 *  block4;
 * 
 * I just remembered it wrong.
 * 
 */
namespace ChatAWhile
{
    class ChatAWhile
    {
        static void Main(string[] args)
        {
            Int16[] AREACODE = { 262, 414, 608, 715, 815, 920 };//get our areacodes
            Double[] RATE = { 0.07, 0.10, 0.05, 0.16, 0.24, 0.14 };//and rates
            String QUIT = "000";//compare inputs with this to quit
            bool quit = false;//flip this switch to kill EVERYTHING.
            while (quit == false)
            {
                Console.WriteLine("Enter \"000\" at any time to quit.");
                Console.Write("Enter in outgoing area code: ");
                String iCode = Console.ReadLine();
                if (iCode == QUIT)//This shows up a lot for obvious reasons
				//pretty much everytime there is input, we play this.
                {
                    quit = true;
                    break;
                }
                Double rate = 0;//this will hold the rate eventually.
                bool digits = false;//Flip this switch once we're sure the input is digits.
                if (iCode.Length == 3)//#2
                {
                    Int16 code;
                    digits = Int16.TryParse(iCode, out code);//#3
                    if (digits == true)//we got numbers! keep going!
                    {                        
                        for (int x = 0; x < AREACODE.Length; x++)//Compare all the area codes.
                        {
                            if (code == AREACODE[x])//and get the matching rate
                            {
                                rate = RATE[x];
                            }
                        }
                        if (rate > 0.001)//#1
                        {
                            bool dDigits = false;//again, check for digits. could have been a seperate method.
                            String iMinutes = "";
                            Double minutes = 0;//made this double so you can have 2.5 minutes
                            while (dDigits == false)
                            {
                                Console.Write("Enter in call length (in Minutes): ");
                                iMinutes = Console.ReadLine();
                                if (iMinutes == QUIT)//again...
                                {
                                    quit = true;
                                    break;
                                }
                                dDigits = Double.TryParse(iMinutes, out minutes);//#3
                                if (dDigits == false)//#3 not digits
                                    Console.WriteLine("Minutes must be entered in DIGITS. Please Try Again.");
                            }
                            Double price = 0;//the total price ends up here
                            price = rate * minutes;//see?
                            String Srate = rate.ToString("C");
                            String Sprice = price.ToString("C");
                            Console.WriteLine();
                            Console.WriteLine("In Area Code {1}, it costs {0} to make a call.", Srate, code);
                            Console.WriteLine("Your call lasted {0} minutes and thus cost {1}.", minutes, Sprice);
                            Console.WriteLine();
                        }//#1
                        else//entered wrong area code
                        {
                            Console.WriteLine("ChatAWhile does not provide service for Area Code {0}.", code);
                            Console.WriteLine("Please Try Again.");
                            Console.WriteLine();
                        }
                    }
                    else//#3 didn't enter digits
                    {
                        Console.WriteLine("Area Codes must be three DIGITS. Please Try Again.");
                        Console.WriteLine();
                    }
                }
                else//#2 more than three characters
                {
                    Console.WriteLine("Area Codes must be THREE digits. Please Try Again.");
                    Console.WriteLine();
                }
            }
        }
    }
}
