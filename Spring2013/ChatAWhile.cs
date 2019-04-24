//[2018-04-18] This was one of my early Community College programs. My professor told me to keep this for my portfolio someday, so here it is. 
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

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
                if (iCode == QUIT)
                {
                    quit = true;
                    break;
                }
                Double rate = 0;
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
                            price = rate * minutes;
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
