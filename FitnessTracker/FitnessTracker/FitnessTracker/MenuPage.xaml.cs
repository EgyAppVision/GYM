using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace FitnessTracker
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class MenuPage : ContentPage
    {
        public MenuPage()
        {
            InitializeComponent();
            var tapGestureRecognizer = new TapGestureRecognizer();
           tapGestureRecognizer.Tapped += async(s, e) => {
              await  Navigation.PushAsync(new Login());
            };
            myInfoStackLy.GestureRecognizers.Add(tapGestureRecognizer);


        }

      
    }
}