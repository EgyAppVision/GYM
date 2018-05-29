using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Newtonsoft.Json;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;
using static FitnessTracker.InitialData;

namespace FitnessTracker
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class NewPostPage : ContentPage
    {
        Player Playeruser;
        List<UserPrefaredActivity> activityList;
        int rows = 2;

        public NewPostPage(Player user)
        {
            InitializeComponent();

            Playeruser = new Player();
            Playeruser = user;

            this.Title = "Cardio";
            activityList = new List<UserPrefaredActivity>();
            GetSignUpDataFromLocalStorage();


            AddNewRow(1);

            var tapGestureRecognizer1 = new TapGestureRecognizer();
            tapGestureRecognizer1.Tapped += (s, e) =>
            {
                AddNewRow(rows);
                rows++;

            };
            addImage.GestureRecognizers.Add(tapGestureRecognizer1);





            var tapGestureRecognizer = new TapGestureRecognizer();
            tapGestureRecognizer.Tapped += (s, e) =>
            {
                pickImage();
            };
            //postImg.GestureRecognizers.Add(tapGestureRecognizer);



            var tapImageGesture = new TapGestureRecognizer();
            tapGestureRecognizer.Tapped += (s, e) =>
            {
                // pickImage();
            };
           // CheckInImg.GestureRecognizers.Add(tapGestureRecognizer);


        }
        public async void GetSignUpDataFromLocalStorage()
        {
            if (Application.Current.Properties.ContainsKey("signUpData") && Application.Current.Properties["signUpData"] != null)
            {
                RootObject signupDataObj = Application.Current.Properties["signUpData"] as RootObject;

                //   RootObject Data = JsonConvert.DeserializeObject<RootObject>(signupDataStr);
                activityList = signupDataObj.userPrefaredActivity;
              
               // placesList = signupDataObj.userPrefaredPlace;

            }

            else
            {
                GetSignUpData();
            }



        }


        async void GetSignUpData()
        {
            string address = "loadDataServices/getSingupData";
            Request request = new Request();
            var response = await request.callService(address, "", "GET");
            if (response.status == true)
            {
                RootObject DataFresh = JsonConvert.DeserializeObject<RootObject>(response.content);

                if (Application.Current.Properties.ContainsKey("signUpData"))
                {
                    Application.Current.Properties["signUpData"] = DataFresh;

                }

                else
                {
                    Application.Current.Properties.Add("signUpData", DataFresh);
                }

                activityList = DataFresh.userPrefaredActivity;
                List<string> values = new List<string>();
                foreach(var ac in activityList)
                {
                    values.Add(ac.value);
                }
                var children = setsGrid.Children.ToList(); 

                foreach (var child in children)
                {
                    if(child is Picker)


                    {
                        var picker = (Picker)child;
                        picker.ItemsSource = values;
                        picker.SelectedIndex = 0;

                    }
                }


               
                //placesList = DataFresh.userPrefaredPlace;

                //preferedActivityLb.Text = activitiesList.Count() != 0 ? activitiesList.Find(a => a.id == user.preferedActivity).value : "";
               // preferedPlaceLb.Text = placesList.Count != 0 ? placesList.Find(p => p.id == user.preferedPlace).value : "";

            }
            else
            {
                await DisplayAlert("Error", "Network Error", "OK");
                // await Navigation.PushAsync(new MainPage());
            }

        }



        void AddNewRow(int row)
        {


            Label setNo = new Label();
            setNo.FontSize = 10;
            setNo.TextColor = Color.Gray;
            setNo.Text = row.ToString();
            setNo.FontSize = 15;

            setNo.HorizontalOptions = LayoutOptions.Center;
            setNo.VerticalOptions = LayoutOptions.End;


            Picker partsPicker = new Picker();
            partsPicker.TextColor = Color.FromHex("#1bbc9b");
            partsPicker.HorizontalOptions = LayoutOptions.FillAndExpand;
            partsPicker.VerticalOptions = LayoutOptions.FillAndExpand;

            //partsPicker.ItemsSource = activityList;
            foreach(var act in activityList)
            {
                partsPicker.Items.Add(act.value);
            }
            partsPicker.SelectedIndex = 0;

            Entry repsEntry = new Entry();
            repsEntry.FontSize = 15;
            repsEntry.TextColor = Color.FromHex("#1bbc9b");
            repsEntry.HorizontalOptions = LayoutOptions.FillAndExpand;
            repsEntry.VerticalOptions = LayoutOptions.End;
            repsEntry.Keyboard = Keyboard.Numeric;

            Entry weightEntry = new Entry();
            weightEntry.FontSize = 15;
            weightEntry.TextColor = Color.FromHex("#1bbc9b");
            weightEntry.HorizontalOptions = LayoutOptions.FillAndExpand;
            weightEntry.VerticalOptions = LayoutOptions.End;
            weightEntry.Keyboard = Keyboard.Numeric;


           
            
            Image deleteImage = new Image();
            deleteImage.Source = "deleteIcon.png";
            deleteImage.HorizontalOptions = LayoutOptions.Center;
            deleteImage.VerticalOptions = LayoutOptions.Center;

            var tapGestureRecognizer2 = new TapGestureRecognizer();
            tapGestureRecognizer2.Tapped += (s, e) =>
            {
                var img = (Image)s;
                var rowNo = Grid.GetRow(img);

                DeleteRow(rowNo);

            };
            deleteImage.GestureRecognizers.Add(tapGestureRecognizer2);





            setsGrid.Children.Add(setNo, 0, row);
            setsGrid.Children.Add(repsEntry, 2, row);
            setsGrid.Children.Add(weightEntry, 3, row);
            setsGrid.Children.Add(partsPicker, 1, row);
            setsGrid.Children.Add(deleteImage, 4, row);



            excercise excerciseObj = new excercise();
            //  excerciseObj.exerciseDescription = 
        }




        void DeleteRow(int rowNo)
        {
            var children = setsGrid.Children.ToList();
            foreach (var child in children.Where(child => Grid.GetRow(child) == rowNo))
            {
                setsGrid.Children.Remove(child);
            }

            rows--;

            foreach (var child in children.Where(child => Grid.GetRow(child) > rowNo))
            {
                Grid.SetRow(child, Grid.GetRow(child) - 1);
            }
        }



        private void SubmitButton_Clicked(object sender, EventArgs e)
        {
            CallSubmitWorkoutService();

        }


        async void CallSubmitWorkoutService()
        {
            if(setsGrid.Children.ToList().Where(child => Grid.GetRow(child) > 0).Count()==0)
            {
                await DisplayAlert("Error!", "Please Fill the Fields first", "Ok");
                return;
            }



            Request addWorkoutRequest = new Request();

            workout myWorkout = new workout();
            myWorkout.workOutDes = workoutNoteTxt.Text;
            myWorkout.workoutTrainee = Playeruser.userId;

            excercise excersise = new excercise();
            List<excercise> excerciseList = new List<excercise>();

            var allChildren = setsGrid.Children.ToList();

            for (int i = 1; i < rows; i++)
            {
                var childs = allChildren.Where(child => Grid.GetRow(child) == i);
                excersise = new excercise();
                string activity = "";
                string distance = "";
                string duration = "";
                foreach (var child in childs)
                {
                    if (child is Picker)
                    {
                        var view = (Picker)child;

                        activity = view.SelectedItem.ToString();

                    }
                        
                    

                    else if (child is Entry)
                    {
                        var view = (Entry)child;

                        if (Grid.GetColumn(child) == 2)
                        {
                            distance = 
                                string.IsNullOrEmpty( view.Text) ? "": view.Text.ToString();
                        }

                        else if (Grid.GetColumn(child) == 3)
                        {
                            duration = string.IsNullOrEmpty(view.Text) ? "" : view.Text.ToString();
                        }

                    }


                }
                excersise.excericeId = i;
                excersise.excericeDescreption = "Excercise " + i + " is " + activity;
                if (!string.IsNullOrEmpty(distance)) excersise.excericeDescreption +=

                    "  distance of " + distance + "km ";

                if(!string.IsNullOrEmpty(duration)) excersise.excericeDescreption +=" for "+
                duration + "  minutes";

                excerciseList.Add(excersise);
            }

            myWorkout.workoutExcerices = excerciseList;
            string workoutStr = JsonConvert.SerializeObject(myWorkout);

           var res=    await addWorkoutRequest.callService("Workout/addworkout", workoutStr, "POST");
            if(res.status == true)
            {
                if (res.content == "STATUS:-3")
                {
                    await DisplayAlert("Error!", "Server Error has occured please try again later!", "Ok");
                    return;
                }

                else if (res.content == "STATUS:-1")
                {
                    await DisplayAlert("Error!", " Invalid Parameters Please enter valid info", "Ok");
                    return;
                }
                await DisplayAlert("Published!", "Your work out has been published successfully", "Ok");
                clearFields();

            }
            else
            {
                await DisplayAlert("Error", "Network Error!", "Ok");

            }
        }

        async void pickImage()
        {
            Stream stream = await DependencyService.Get<IPicturePicker>().GetImageStreamAsync();

            if (stream != null)
            {
                //postImg.Source = ImageSource.FromStream(() => stream);
            
            }
        }

        void clearFields()
        {
            var childs = setsGrid.Children.ToList();
            workoutNoteTxt.Text = "";
            foreach (var child in childs)
            {
                if (Grid.GetRow(child) > 0)
                {
                    setsGrid.Children.Remove(child);
                }
              
             
            }

            AddNewRow(1);

        }




    }
}