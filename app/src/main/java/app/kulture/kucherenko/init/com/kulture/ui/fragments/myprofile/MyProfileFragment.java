package app.kulture.kucherenko.init.com.kulture.ui.fragments.myprofile;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.Objects;

import app.kulture.kucherenko.init.com.kulture.R;
import app.kulture.kucherenko.init.com.kulture.client.Request;
import app.kulture.kucherenko.init.com.kulture.events.BuyMemberEvent;
import app.kulture.kucherenko.init.com.kulture.events.BuyUnlimitedEvent;
import app.kulture.kucherenko.init.com.kulture.interfaces.ILoadingStatus;
import app.kulture.kucherenko.init.com.kulture.interfaces.UploadUserAvatar;
import app.kulture.kucherenko.init.com.kulture.interfaces.UserInfoLoadingStatus;
import app.kulture.kucherenko.init.com.kulture.models.ClassItemModel;
import app.kulture.kucherenko.init.com.kulture.models.MyProfileModel;
import app.kulture.kucherenko.init.com.kulture.models.user.UserInfoModel;
import app.kulture.kucherenko.init.com.kulture.settings.MSharedPreferences;
import app.kulture.kucherenko.init.com.kulture.ui.activity.signin.SignInActivity;
import app.kulture.kucherenko.init.com.kulture.utils.SimpleAlert;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;

public class MyProfileFragment extends Fragment implements ILoadingStatus<List<ClassItemModel>>, UserInfoLoadingStatus, UploadUserAvatar<String> {

    public static final String TAG = "0x008800";
    private int RESULT_LOAD_IMAGE = 10;
    private String PREFS_NAME = "image";
    private Context mContext;

    MyProfileModel myProfileModel = new MyProfileModel();

    AlertDialog mAdSetUserAvatar;

    private String[] mCategories = myProfileModel.getmCategories();
    private String[] mDescription = myProfileModel.getmDescription();
    private int[] mImageIds = myProfileModel.getmImageIds();

    public static final String SHARED_PACKAGE = "user_info";

    private View view;

    private AlertDialog mAdLogout;

    //info user
    private UserInfoModel mUserInfo;
    private CircleImageView mIvUserAvatar;
    private TextView userFirstName;
    private TextView userLastName;

    public static MyProfileFragment newInstance() {
        return new MyProfileFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my_profile, container, false);

        mContext = view.getContext();

        mIvUserAvatar = view.findViewById(R.id.iv_user_image);
        userFirstName = view.findViewById(R.id.tv_user_first_name);
        userLastName = view.findViewById(R.id.tv_user_last_name);

        setAvatar();

        Log.e(TAG, "onCreateView: end");
        return view;

    }

    private Fragment getActivityStarterFragment() {
        if (getParentFragment() != null) {
            return getParentFragment();
        }
        return this;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e(TAG, "onActivityResult: start");

        super.onActivityResult(requestCode, resultCode, data);
        try {
            //requestCode >>= 16;
            requestCode = 10;
            Log.e(TAG, "onActivityResult: reques = " + requestCode + " result = " + resultCode + " data = " + data.getDataString());
            if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK
                    && null != data) {
                Log.e(TAG, "onActivityResult: if");
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = view.getContext().getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String picturePath = cursor.getString(columnIndex);
                cursor.close();
                setPreference(mContext, picturePath, "imagePath");
                mIvUserAvatar.setImageBitmap(getScaledBitmap(picturePath, 800, 800));

                uploadAvatarOnServer(getRealPathFromURIPath(selectedImage));
                Log.e(TAG, "onActivityResult: end");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private String getRealPathFromURIPath(Uri contentURI) {
        Cursor cursor = view.getContext().getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            return contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }
    }

    private void setAvatar() {

        if (getAvatarFromServer()) return;
        else if (getAvatarFromFaceBook()) return;
        else
            SimpleAlert.show(mContext, "Hint", "You can set your personal photo, just make long touch on it", "Ok");
    }

    private boolean getAvatarFromPhone() {
        Log.e(TAG, "getAvatarFromPhone: start");
        try {
            String path = getPreference(mContext, "imagePath");
            if (path == null || path.length() == 0 || path.equalsIgnoreCase("")) {
                return false;
            } else {
                mIvUserAvatar.setImageBitmap(getScaledBitmap(path, 800, 800));
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean getAvatarFromServer() {
        Log.e(TAG, "getAvatarFromServer: start");

        Request.getInstance().getUserInfo(this);
        return true;
    }

    private boolean getAvatarFromFaceBook() {


        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/{user-id}/picture",
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
            /* handle the result */
                        response.getRequest();
                    }
                }
        ).executeAsync();

        Log.e(TAG, "getAvatarFromFaceBook: start");

        Uri imageUri = Profile.getCurrentProfile().getProfilePictureUri(400, 400);
        Glide.with(mContext).load(imageUri).into(mIvUserAvatar);
        uploadAvatarOnServer(imageUri.toString());
        return true;
    }

    private boolean uploadAvatarOnServer(String path) {

        Log.e(TAG, "uploadAvatarOnServer: start");
        Request.getInstance().uploadAvatar(path, this);
        return true;
    }

    @Override
    public void onStart() {
        Log.e(TAG, "onStart: start");
        super.onStart();

        View view = getView();
        if (view != null) {

            final RecyclerView recyclerView = view.findViewById(R.id.rv_my_profile);

            recyclerView.setNestedScrollingEnabled(true);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
            recyclerView.setLayoutManager(linearLayoutManager);
            final RecyclerAdapterMyProfile adapter = new RecyclerAdapterMyProfile(mCategories, mDescription, mImageIds, view, this);

            mUserInfo = new Gson().fromJson(MSharedPreferences.getInstance().getUserInfo(), UserInfoModel.class);

            try {
                userFirstName.setText(Objects.equals(mUserInfo.getFirstName(), "") ?
                        "FirstName" : mUserInfo.getFirstName());

                userLastName.setText(Objects.equals(mUserInfo.getLastName(), "") ?
                        "LastName" : mUserInfo.getLastName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            recyclerView.setAdapter(adapter);

            // обработка нажатий по элементу списка
//            ItemClickSupport.addTo(recyclerView).setOnItemClickListener((recyclerView1, position, v) -> {
//                goToInfo(position);
//            });
        }

        //logout button
        ImageView button = view.findViewById(R.id.imageView_myProfile_logout);

        button.setOnClickListener(view1 -> {
            mAdLogout = new AlertDialog.Builder(view.getContext()).create();
            mAdLogout.setMessage("Go out?");
            mAdLogout.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel",
                    (dialog, which) -> mAdLogout.cancel());
            mAdLogout.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                    (dialog, which) -> {
                        startActivity(new Intent(getActivity(), SignInActivity.class));
                        MSharedPreferences.getInstance().setKey(null);
                        MSharedPreferences.getInstance().setBraintreeToken(null);
                        LoginManager.getInstance().logOut();
                        Request.getInstance().logOut();
                        getActivity().finish();
                    });
            mAdLogout.show();

        });

        mIvUserAvatar.setOnLongClickListener(view1 -> {
            mAdSetUserAvatar = new AlertDialog.Builder(view1.getContext()).create();
            mAdSetUserAvatar.setTitle("chose your action");
            mAdSetUserAvatar.setMessage("chose: ");
            mAdSetUserAvatar.setButton(AlertDialog.BUTTON_NEUTRAL, "camera", (dialogInterface, i) -> {
                Toast.makeText(view1.getContext(), "Camera", Toast.LENGTH_LONG).show();
                // TODO: 29.11.17 make camera pick photo
                dialogInterface.dismiss();
            });
            mAdSetUserAvatar.setButton(AlertDialog.BUTTON_POSITIVE, "Gallery", (dialogInterface, i) -> {
                //Toast.makeText(view1.getContext(), "Gallery", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(
                        Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, RESULT_LOAD_IMAGE);

                //dialogInterface.dismiss();

            });
            mAdSetUserAvatar.show();
            // getActivity().finish();

            return true;
        });
        Log.e(TAG, "onStart: end");
    }

    private Bitmap getScaledBitmap(String picturePath, int width, int height) {
        Log.e(TAG, "getScaledBitmap: start");
        BitmapFactory.Options sizeOptions = null;
        try {
            sizeOptions = new BitmapFactory.Options();
            sizeOptions.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(picturePath, sizeOptions);

            int inSampleSize = calculateInSampleSize(sizeOptions, width, height);

            sizeOptions.inJustDecodeBounds = false;
            sizeOptions.inSampleSize = inSampleSize;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Log.e(TAG, "getScaledBitmap: end");
        return BitmapFactory.decodeFile(picturePath, sizeOptions);
    }

    private int calculateInSampleSize(BitmapFactory.Options options,
                                      int reqWidth, int reqHeight) {
        Log.e(TAG, "calculateInSampleSize: start");
        int inSampleSize = 0;
        try {
            // Raw height and width of image
            final int height = options.outHeight;
            final int width = options.outWidth;
            inSampleSize = 1;

            if (height > reqHeight || width > reqWidth) {

                // Calculate ratios of height and width to requested height and
                // width
                final int heightRatio = Math.round((float) height
                        / (float) reqHeight);
                final int widthRatio = Math.round((float) width
                        / (float) reqWidth);

                // Choose the smallest ratio as inSampleSize value, this will
                // guarantee
                // a final image with both dimensions larger than or equal to
                // the
                // requested height and width.
                inSampleSize = heightRatio < widthRatio ? heightRatio
                        : widthRatio;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Log.e(TAG, "calculateInSampleSize: end");
        return inSampleSize;
    }

    boolean setPreference(Context c, String value, String key) {
        Log.e(TAG, "setPreference: start");
        SharedPreferences settings;
        settings = c.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        Log.e(TAG, "setPreference: end");
        return editor.commit();
    }

    String getPreference(Context c, String key) {
        Log.e(TAG, "getPreference: start");
        SharedPreferences settings;
        settings = c.getSharedPreferences(PREFS_NAME, 0);
        Log.e(TAG, "getPreference: end");
        return settings.getString(key, "");

    }


    public void buyMembership() {
        if (!mUserInfo.isIsMember()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("Buy Membership")
                    .setMessage("Are you sure?")
                    .setCancelable(false)
                    .setNegativeButton("Cancel",
                            (dialog, id) -> {
                                dialog.cancel();
                            })
                    .setPositiveButton("OK", (dialog, i) -> {
                        EventBus.getDefault().post(new BuyMemberEvent());
                    });
            builder.show();
        } else
            Toast.makeText(view.getContext(), "You are already a member.", Toast.LENGTH_SHORT).show();
    }

    public void buyUnlimited() {
        if (mUserInfo.isVIP()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("Buy Unlimited UserPack")
                    .setMessage("Are you sure?")
                    .setCancelable(false)
                    .setNegativeButton("Cancel",
                            (dialog, id) -> {
                                dialog.cancel();
                            })
                    .setPositiveButton("OK", (dialog, i) -> {
                        EventBus.getDefault().post(new BuyUnlimitedEvent());
                    });
            builder.show();
        } else
            Toast.makeText(view.getContext(), "You do not have permission to purchase an unlimited member.", Toast.LENGTH_SHORT).show();
    }

    //
    @Override
    public void onSuccess(List<ClassItemModel> info) {
        String[] message;
        if (info.size() > 0) {
            message = new String[info.size()];
            for (int i = 0; i < info.size(); i++) {
                if (info.get(i).getCount() != 0) {
                    message[i] = info.get(i).getClasses().getName() + " - " + info.get(i).getCount();
                }
            }
        } else {
            message = new String[1];
            message[0] = "You do not have Credits package";
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Credits package")
                .setItems(message, (dialog, which) -> {
                })
                .setCancelable(false)
                .setPositiveButton("OK", (dialog, i) -> dialog.cancel());
        builder.show();
    }

    //
    @Override
    public void onFailure(String message) {

    }

    //
    @Override
    public void onUserInfoSuccess(UserInfoModel user) {
        if (user.getImage() != null && !user.getImage().equals("")) {
            Log.e(TAG, "onUserInfoSuccess: glide get starting image = " + user.getImage());

            Glide.with(mContext)
                    .load(getResources().getString(R.string.host) + user.getImage())
                    .override(800, 800)
                    .dontAnimate()
                    .into(mIvUserAvatar);
        }
//
//            Log.e(TAG, "onUserInfoSuccess: upload avatar from server");
//
//        } else Log.e(TAG, "onUserInfoSuccess: not upload avatar from server");
    }

    //
    @Override
    public void onUserInfoFailure(String message) {
        Log.e(TAG, "onUserInfoFailure: ");
    }

    @Override
    public void onSuccessUploadUserAvatar(String info) {
        Log.e(TAG, "onSuccessUploadUserAvatar: " + info);
    }

    @Override
    public void onFailureUploadUserAvatar(String message) {
        Log.e(TAG, "onFailureUploadUserAvatar: " + message);
    }
}