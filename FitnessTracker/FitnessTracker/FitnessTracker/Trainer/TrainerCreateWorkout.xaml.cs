using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace FitnessTracker.Trainer
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class TrainerCreateWorkout : ContentPage
    {
        // user is a trainer 
        User user;
        User player;
        public TrainerCreateWorkout( SessionsRequest sessionRequest)
        {
           
            InitializeComponent();
            this.Title = "Create Workout";
            user = new User(); //trainer
            string userStr = Application.Current.Properties["user"] as string;
            user = JsonConvert.DeserializeObject<User>(userStr);

            player = new User();
            player = player.GetUserById (sessionRequest.userId);



        }

        private void pushWorkOutBtn_Clicked(object sender, EventArgs e)
        {

        }
    }

}