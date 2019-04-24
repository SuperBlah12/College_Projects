using System;
/*
 * 3 people sell tubs.
 * Andrea, Brittney, and Eric
 * while input != Z, ask for amnt person made on sale.
 * calc commission as 10% sale amnt. add to running total.
 * if user types "Z" display all commissions.
*/
    public class TubSales
    {
        static void Main()
        {
            bool quit = false;
            short ANDR = 1;
            short BRIT = 2;
            short ERIC = 3;
            Double AComm = 0;
            Double BComm = 0;
            Double EComm = 0;
            while(quit == false)
            {
                Console.WriteLine("Welcome, Employee. Type \"Z\" at anytime to quit.");
                Console.Write("Please enter your Name: ");
                String input = Console.ReadLine();
                String data = input.ToLower();
                short employee = 0;
                if (input == "andrea")
                {
                    Console.WriteLine("Welcome, Andrea");
                    employee = ANDR;
                }
                if (input == "brittney")
                {
                    Console.WriteLine("Welcome, Brittney");
                    employee = BRIT;
                }
                if (input == "eric")
                {   
                    Console.WriteLine("Welcome, Eric");
                    employee = ERIC;
                }
                else{
                    bool fail = true;
                    while (fail == true)
                    {   
                        Console.WriteLine("Error: Invalid name. Try Again.");
                        Console.Write("Please enter your Name: ");
                        input = Console.ReadLine();
                        data = input.ToLower();
                        if (input == "andrea")
                        {   
                            Console.WriteLine("Welcome, Andrea");
                            employee = ANDR;
                            fail = false;
                        }
                        if (input == "brittney")
                        {
                            Console.WriteLine("Welcome, Brittney");
                            employee = BRIT;
                            fail = false;
                        }
                        if (input == "eric")
                        {
                            Console.WriteLine("Welcome, Eric");
                            employee = ERIC;
                            fail = false;
                        }
                        if (input == "z")
                        {
                            quit = true;
                        }
                        else{
                        }
                    }
                }
                Console.Write("Please enter your profits: $");
                String Sprofit = Console.ReadLine();
                Sprofit = Sprofit.ToLower();
                if (Sprofit == "z")
                {
                    quit = true;
                }
                else
                {
                }
                Double profit = Convert.ToDouble(Sprofit);
                Double comm = profit * .1;
                Double show = 0;
                String Name;
                if (employee == ANDR)
                {
                    AComm = AComm + comm;
                    show = AComm;
                    Name = "Andrea";
                }
                if (employee == BRIT)
                {
                    BComm = BComm + comm;
                    show = BComm;
                    Name = "Brittney";
                }
                if (employee == ERIC)
                {
                    EComm = EComm + comm;
                    show = EComm;
                    Name = "Eric";
                }
                String cash = show.ToString();
                Console.WriteLine("Thank you, {0}. Your Commission is currently ${1}.", Name, cash);
            }
        }
    }