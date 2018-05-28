using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace FitnessTracker
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class Sessions : ContentPage
    {
        public Sessions()
        {
            InitializeComponent();
            BackgroundImage = "PersonalTrainer.jpg";
        }

        private void requestSessionBtn_Clicked(object sender, EventArgs e)
        {
            Navigation.PushAsync(new PlayerRequestSession());
        }

        private void orderedSessionBtn_Clicked(object sender, EventArgs e)
        {
            Navigation.PushAsync(new PlayerSessionList());
        }
    }
}