using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;

namespace FitnessTracker
{
    class SignUpValidation
    {
        public  bool firstNameVal { get; set; }
        public bool lastNameVal { get; set; }
        public static bool emailVal { get; set; }
        public static bool mobileVal { get; set; }
        public bool ageVal { get; set; }
        public static bool passwordVal { get; set; }
        public bool heightVal { get; set; }
        public bool weightVal { get; set; }
        public bool fatPercVal { get; set; }
        public bool placeVal { get; set; }
        public bool activityVal { get; set; }
        public static bool isNotValid { get; set; }


        public const string MatchEmailPattern =
            @"^(([\w-]+\.)+[\w-]+|([a-zA-Z]{1}|[\w-]{2,}))@"
     + @"((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\.([0-1]?
				[0-9]{1,2}|25[0-5]|2[0-4][0-9])\."
     + @"([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\.([0-1]?
				[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
     + @"([a-zA-Z0-9]+[\w-]+\.)+[a-zA-Z]{1}[a-zA-Z0-9-]{1,23})$";


       public const string matchMobilePattern= @"^\s *\+?[0 - 9]\d?[- .] ? (\([2 - 9]\d{ 2}\)|[2 - 9]\d{ 2})[- .]?\d{3}[- .]?\d{4}$";

        public const string matchpasswordPattern = @"^ (?=.*[a - z])(?=.*[A - Z])(?=.*\d).{8,15}$";

        // true means validation failed 
        public static SignUpValidation CheckPage1Validation(Player playerObj)
        {
            SignUpValidation validationObj = new SignUpValidation();
            if (playerObj.firstName.Length < 2 || playerObj.firstName.Length > 13)
            {
                validationObj.firstNameVal = true;
                isNotValid = true;
            }

            if (playerObj.lastName.Length < 2 || playerObj.firstName.Length > 13)
            {
                validationObj.lastNameVal = true;
                isNotValid = true;
            }

            if (playerObj.email.Length > 2)
            {
                emailVal = Regex.IsMatch(playerObj.email, MatchEmailPattern) == true ? false : true;
                isNotValid = emailVal == true?  true : false ;

            }
            else
            {
                emailVal = true;
                isNotValid = true;

            }


            if (playerObj.mobile.Length <= 10 || playerObj.mobile.Length >= 15 || IsDigitsOnly(playerObj.mobile) == false)

            
                mobileVal = false;
                //    mobileVal = Regex.IsMatch(playerObj.mobile,matchMobilePattern ) == true ? false : true;
                isNotValid = mobileVal == true ? true : false;

            if (playerObj.password.Length <= 7)
                passwordVal = false;
            //passwordVal = Regex.IsMatch(playerObj.password, matchpasswordPattern) == true ? false : true;
            isNotValid = passwordVal == true ? true : false;

            return validationObj;

        }



       static bool  IsDigitsOnly(string str)
        {
            foreach (char c in str)
            {
                if (c < '0' || c > '9')
                    return false;
            }

            return true;
        }
    }
}
