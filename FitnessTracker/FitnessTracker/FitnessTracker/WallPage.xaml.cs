using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace FitnessTracker
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class WallPage : ContentPage
    {
        public ListView ListView { get { return listView; } }

        ListView listView;
        User user;
        public WallPage()
        {
            InitializeComponent();


            user = new User();
            string userStr = Application.Current.Properties["user"] as string;
            user = JsonConvert.DeserializeObject<User>(userStr);

            //to be deleted
            //user.id = 22;
            //user.firstName = "User";
            //user.lastName = "Last Name";


           // string userStr = JsonConvert.SerializeObject(user);

            //if (Application.Current.Properties.ContainsKey("user"))
            //{

            //    Application.Current.Properties["user"] = userStr;

            //}

            //else
            //{
            //    Application.Current.Properties.Add("user", userStr);

            //}
            // to be deleted


            var tapGestureRecognizer = new TapGestureRecognizer();
            tapGestureRecognizer.Tapped +=  (s, e) => {
                ShowAction();
            };
            newPostImg.GestureRecognizers.Add(tapGestureRecognizer);


            // to be deleted
            BindWall("Radwa Alaa" , "profile.jpg", "trainingImg2.png","Good Workout" , "Excercise 1 is 20 Reps with 15kg  for Bisebs\n,Excercise 2 is 10 Reps with 10kg  for Bench");
            BindWall("Wagdy Negm", "profile.jpg", "trainingImg1.png","Cardio Day", "Excercise 1 is Running  distance of 30km  for 40  minutes\n,Excercise 2 is Cycling  distance of 20km  for 30  minutes");
            BindWall("Mohamed Nageeb", "profile.jpg", "trainingImg2.png","I love Boxing", "Excercise 1 is Boxing for 40  minutes") ;
        }

        async void  ShowAction()
        {
            var action = await DisplayActionSheet("choose an activity", "Cancel", null, "Cardio", "Weight Lifting");
            if (action == "Cardio")
            {
              await  Navigation.PushAsync(new NewPostPage(user));
            }
            else
            {
              await  Navigation.PushAsync(new AddNewWLActivity(user));
            }

        }

        public void fillWall(User playerObj)
        {
            var masterPageItems = new List<MasterPageItem>();
            masterPageItems.Add(new MasterPageItem
            {
                Title = user.firstName,
                IconSource = "contacts.png",
                TargetType = typeof(PlayerProfilePage)
            });
            masterPageItems.Add(new MasterPageItem
            {
                Title = "Notifications",
                IconSource = "reminders.png",
                TargetType = typeof(Notifications)
            });
            masterPageItems.Add(new MasterPageItem
            {
                Title = "LogOut",
                IconSource = "todo.png",
                TargetType = typeof(LogOutCS)
            });

            listView = new ListView
            {
                ItemsSource = masterPageItems,
                ItemTemplate = new DataTemplate(() =>
                {
                    var grid = new Grid { Padding = new Thickness(5, 10) };
                    grid.ColumnDefinitions.Add(new ColumnDefinition { Width = new GridLength(30) });
                    grid.ColumnDefinitions.Add(new ColumnDefinition { Width = GridLength.Star });
                    var image = new Image();
                    image.SetBinding(Image.SourceProperty, "IconSource");
                    var label = new Label { VerticalOptions = LayoutOptions.FillAndExpand };
                    label.SetBinding(Label.TextProperty, "Title");

                    grid.Children.Add(image);
                    grid.Children.Add(label, 1, 0);


                    var feedimage = new Image()
                    {
                        Source = "bg.jpg"
                    };



                    return new ViewCell { View = grid };
                }),
                SeparatorVisibility = SeparatorVisibility.None
            };
        }
        protected override bool OnBackButtonPressed()
        {
            return true; 
        }



        void BindWall(string username , string profilePic , string postimage , string comment,string description)
        {


            StackLayout stack3 = new StackLayout();
            stack3.Orientation = StackOrientation.Vertical;
            stack3.Margin = new Thickness(20, 20, 20, 35);
            stack3.BackgroundColor = Color.White;

            //fill the upper stack
            StackLayout stack31 = new StackLayout();
            stack31.Orientation = StackOrientation.Horizontal;
            stack31.Margin = new Thickness(10, 10, 10, 10);


            Image userImg = new Image();
            userImg.Margin = new Thickness(10, 0, 10, 0);
            userImg.HeightRequest = 30;
            userImg.WidthRequest = 30;
            userImg.Source = profilePic; // from data

            StackLayout stack311 = new StackLayout();
            stack311.Spacing = 0.5;
            stack311.Orientation = StackOrientation.Vertical;
            stack311.VerticalOptions = LayoutOptions.Center;


            Label usernameLb = new Label();
            usernameLb.TextColor = Color.FromHex("#595959");
            usernameLb.FontSize = 20;
            usernameLb.Text = username; //from data


            Label timeLb = new Label();
            timeLb.TextColor = Color.LightGray;
            timeLb.VerticalOptions = LayoutOptions.Start;
            timeLb.FontSize = 15;
            timeLb.Text = "5 hr ago"; //from data
            stack311.Children.Add(usernameLb);
            stack311.Children.Add(timeLb);
            stack31.Children.Add(userImg);
            stack31.Children.Add(stack311);



            //fill the middle stack

          
            StackLayout stack32 = new StackLayout();
            stack32.Orientation = StackOrientation.Horizontal;
            stack32.HeightRequest = 350;

            Image posteImg = new Image();
            posteImg.Margin = new Thickness(10, 10, 10, 10);
            posteImg.HorizontalOptions = LayoutOptions.FillAndExpand;
            posteImg.Aspect = Aspect.AspectFill;
            posteImg.Source = postimage; //from data


            stack32.Children.Add(posteImg);



            //fill down stack
            StackLayout stack33 = new StackLayout();
            stack33.Margin = new Thickness(10, 0, 10, 10);
            stack33.Orientation = StackOrientation.Vertical;
            Label commentLb = new Label();
            commentLb.Text = comment;
            commentLb.TextColor = Color.DarkOliveGreen;
            commentLb.HorizontalOptions = LayoutOptions.Start;
            commentLb.FontAttributes = FontAttributes.Bold;


            Label descriptionLb = new Label();
            descriptionLb.TextColor = Color.Silver;
            descriptionLb.Text = description;  //from data

            stack33.Children.Add(commentLb);
            stack33.Children.Add(descriptionLb);
            containerStack.Children.Add(stack3);
            stack3.Children.Add(stack31);
            stack3.Children.Add(stack32);
            stack3.Children.Add(stack33);


        }
    }
}