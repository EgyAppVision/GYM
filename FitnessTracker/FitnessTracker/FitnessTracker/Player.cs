using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FitnessTracker
{
   
        public class User
        {
            public int userId { get; set; }
            public string firstName { get; set; }
        public string userName { get; set; }
        public string lastName { get; set; }
            public double weight { get; set; }
            public double height { get; set; }
            public double fatPercentage { get; set; }
            public string email { get; set; }
            public string password { get; set; }
            public int gender { get; set; }
        public string mobile { get; set; }
        public string place { get; set; }
        public int activity { get; set; }
        public int type { get; set; }
        public string birthDate { get; set; }
        public int isFollowing { get; set; }
        public int preferedPlace { get; set; }
        public int preferedActivity { get; set; }












        public User GetUserById(int id)
        {
            User user = new User();
            // Request req = 

            return user;
            
        }
    }




}
