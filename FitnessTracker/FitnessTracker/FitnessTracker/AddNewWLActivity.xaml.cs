using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace FitnessTracker
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class AddNewWLActivity : ContentPage
    {
        int rows = 2;
        User player;
        List<string> MusclesList;
        List<excercise> excersiseList;

        public AddNewWLActivity( User user)
        { 
            player = new User();
            player = user;
            string userStr  =  Application.Current.Properties["user"] as string; // from device memory
            player = JsonConvert.DeserializeObject<User>(userStr);


            this.Title = "Weight Lifting";
            InitializeComponent();

            MusclesList = new List<string>();
            MusclesList.Add("Bisebs");
            MusclesList.Add("Trisebs");
            MusclesList.Add("Bench");
            MusclesList.Add("Legs");

            AddNewRow(1);


            var tapGestureRecognizer = new TapGestureRecognizer();
            tapGestureRecognizer.Tapped += (s, e) =>
            {
                AddNewRow(rows);
                rows++;

            };
            addImage.GestureRecognizers.Add(tapGestureRecognizer);


           

             excersiseList = new List<excercise>();


        }




       async  void SendWorkoutRequest()
        {
            if (setsGrid.Children.ToList().Where(child => Grid.GetRow(child) > 0).Count() == 0)
            {
                await DisplayAlert("Error!", "Please Fill the Fields first", "Ok");
                return;
            }

            Request addWorkoutRequest = new Request();

            workout myWorkout = new workout();
            myWorkout.workOutDes = workoutNoteTxt.Text;
            myWorkout.workoutTrainee = player.userId;

            excercise excersise = new excercise();
            List<excercise> excerciseList = new List<excercise>();

            var allChildren = setsGrid.Children.ToList();
            
            for (int i = 1; i < rows; i++)
            {
                var childs = allChildren.Where(child => Grid.GetRow(child) == i);
                excersise = new excercise();
                string reps = "";
                string weight = "";
                string muscle = "";
                foreach (var child in childs)
                {
                    //excerciseDesc = " excercise no " + i + "  is : ";
                    if (child is Entry)
                    {//validations

                        var view = (Entry)child;

                        if (Grid.GetColumn(child) == 1)
                        {
                            
                            reps = string.IsNullOrEmpty(view.Text) ? "" : view.Text;
                        }

                        else if (Grid.GetColumn(child) == 2)
                        {
                            weight = string.IsNullOrEmpty(view.Text) ? "" : view.Text;
                        }
                    }

                    else if (child is Picker)
                    {
                        var view = (Picker)child;

                        muscle = view.SelectedItem.ToString();
                    }

                   
                }
                excersise.excericeId = i;
                excersise.excericeDescreption = "Excercise " + i + " is ";
                if (!string.IsNullOrEmpty(reps)) excersise.excericeDescreption += reps +
                     " Reps ";
                if (!string.IsNullOrEmpty(weight)) excersise.excericeDescreption += "with " + weight + "kg";

                excersise.excericeDescreption+=  "  for " + muscle;
                excerciseList.Add(excersise);
            }

           // excersiseList.Remove(excersiseList.Last());
            myWorkout.workoutExcerices = excerciseList;
            string workoutStr = JsonConvert.SerializeObject(myWorkout);

           var response =   await addWorkoutRequest.callService("Workout/addworkout", workoutStr, "POST");
            if(response.status == true)
            {
                if(response.content == "STATUS:-3")
                {
                    await DisplayAlert("Error!", "Server Error has occured please try again later!", "Ok");
                    return;
                }

                else if (response.content == "STATUS:-1")
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





        void AddNewRow(int row)
        {
            

            Label setNo = new Label();
            setNo.FontSize = 10;
            setNo.TextColor = Color.Gray;
            setNo.Text = row.ToString();
            setNo.FontSize = 15;

            setNo.HorizontalOptions = LayoutOptions.Center;
            setNo.VerticalOptions = LayoutOptions.End;

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


            Picker partsPicker = new Picker();
            partsPicker.TextColor= Color.FromHex("#1bbc9b");
            partsPicker.HorizontalOptions = LayoutOptions.FillAndExpand;
            partsPicker.VerticalOptions = LayoutOptions.FillAndExpand;
           
            partsPicker.ItemsSource = MusclesList;
            partsPicker.SelectedIndex = 0;
            //TimePicker timePicker = new TimePicker();
            //timePicker.TextColor = Color.FromHex("#1bbc9b");
            //timePicker.HorizontalOptions = LayoutOptions.Center;
            //timePicker.VerticalOptions = LayoutOptions.Center;

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


            


            setsGrid.Children.Add(setNo,0,row);
            setsGrid.Children.Add(repsEntry,1,row);
            setsGrid.Children.Add(weightEntry,2,row);
            setsGrid.Children.Add(partsPicker,3,row);
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
            SendWorkoutRequest();
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