using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FitnessTracker
{
   public class InitialData
    {
        public class UserGender
        {
            public int id { get; set; }
            public string value { get; set; }
        }

        public class UserPrefaredPlace
        {
            public int id { get; set; }
            public string value { get; set; }
        }

        public class UserPrefaredActivity
        {
            public int id { get; set; }
            public string value { get; set; }
        }

        public class UserType
        {
            public int id { get; set; }
            public string value { get; set; }
        }



        public class RootObject
        {
            public List<UserGender> userGender { get; set; }
            public List<UserPrefaredPlace> userPrefaredPlace { get; set; }
            public List<UserPrefaredActivity> userPrefaredActivity { get; set; }
            public List<UserType> userType { get; set; }
        }

    }
}
