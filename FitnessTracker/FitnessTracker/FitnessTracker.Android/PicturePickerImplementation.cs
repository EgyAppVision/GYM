using System;
using System.IO;
using System.Threading.Tasks;

using Android.Content;

using Xamarin.Forms;
using FitnessTracker.Droid;

[assembly: Dependency(typeof(PicturePickerImplementation))]

namespace FitnessTracker.Droid
{
    class PicturePickerImplementation : IPicturePicker
    {
        public Task<Stream> GetImageStreamAsync()
        {
            // Define the Intent for getting images
            Intent intent = new Intent();
            intent.SetType("image/*");
            intent.SetAction(Intent.ActionGetContent);

            // Get the MainActivity instance
            MainActivity activity = Forms.Context as MainActivity;
            //        public static readonly int PickImageId = 1000;

            // Start the picture-picker activity (resumes in MainActivity.cs)
            activity.StartActivityForResult(
                Intent.CreateChooser(intent, "Select Picture"),1000);
            // Save the TaskCompletionSource object as a MainActivity property
            activity.PickImageTaskCompletionSource = new TaskCompletionSource<Stream>();

            // Return Task object
            return activity.PickImageTaskCompletionSource.Task;


        }
    }
}