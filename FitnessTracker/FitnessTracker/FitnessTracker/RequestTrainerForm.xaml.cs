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
    public partial class RequestTrainerForm : ContentPage
    {
        public RequestTrainerForm( Trainer.Trainer trainerObj)
        {
            this.Title = "Request Form for " + trainerObj.name;
            InitializeComponent();
            for(int i = 1; i <= 50; i++)
            {
                sessionnymberPicker.Items.Add(i.ToString());
            }

            sessionnymberPicker.SelectedIndex = 0;
        }

        private void CancelBtn_Clicked(object sender, EventArgs e)
        {
            Navigation.PopAsync();
        }
    }
}