using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FitnessTracker.Trainer
{
   public class SessionsRequest
    {
        public int id { get; set; }
        public int trainerId { get; set; }
        public int  userId{ get; set; }
        public int numberOfSessions { get; set; }
        public string userName { get; set; }
        public string userNote { get; set; }


    }
}
