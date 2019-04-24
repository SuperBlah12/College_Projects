using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace InchesToFeet
{
    class InchesToFeet
    {
        static void Main(string[] args)
        {
            bool quit = false;
            String QUITTEXT = "!";
			int inches = 1;
            while (inches >= 0)
            {
                inches = getNumeric("Input number of inches: ")
                int feet = getFeet(inches);
                int rem = inches - (feet * 12);
                Console.WriteLine("{0} inches is equal to {1} foot and {2} inches.", inches, feet, rem);
                Console.WriteLine();
            }
        }
         public static int getFeet(Int32 inch){
             int feet = inch/12;
             return feet;
         }
		 
		 //Only returns negative if input = quitText
		 public static int getPositiveNumber(String prompt, String quitText){
			Console.WriteLine("Type \"{1}\" to exit", quitText);
                bool goodInput = true;
                int inches = 0;
				do
                {
                    if(!goodInput)
						Console.WriteLine("Input not in digits. Try Again.");
                    Console.Write(prompt);
                    inputString = Console.ReadLine();
					if (inputString == quitText)
						return -1;
                    goodInput = int.TryParse(inputString, out number);
					if(goodInput){
						if(number < 0)
							return number * (-1);
						else
							return number;
					}
                }while (goodInput == false);
		 }
		 
    }
}
