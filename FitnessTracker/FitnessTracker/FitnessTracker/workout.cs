using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FitnessTracker
{
   public class workout
    {
        public string workOutDes { get; set; }
        public int workoutTrainee { get; set; }
        public  List<excercise> workoutExcerices { get; set; }
    }



    public class excercise
    {
        public int excericeId { get; set; }
        public string excericeDescreption { get; set; }
    }
}
