package app.kulture.kucherenko.init.com.kulture.ui;

import android.app.Application;
import android.content.res.Resources;

import com.google.firebase.FirebaseApp;

import java.util.ArrayList;
import java.util.List;

import app.kulture.kucherenko.init.com.kulture.models.ApplicationData;
import app.kulture.kucherenko.init.com.kulture.models.TokenModel;
import app.kulture.kucherenko.init.com.kulture.models.classes.AllDayClassModel;
import app.kulture.kucherenko.init.com.kulture.models.classes.Teacher;
import app.kulture.kucherenko.init.com.kulture.models.user.UserInfoModel;

/**
 * @author Alex Kucherenko(Godsmack)
 */
public class MApplication extends Application {

    private Resources res;
    private TokenModel token;
    private static MApplication instance;
    private List<AllDayClassModel> allClasses;
    private List<AllDayClassModel> futureClasses;
    private List<Teacher> teachers;
    private ApplicationData applicationData;
    private UserInfoModel userInfo;

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
        instance = this;
        allClasses = new ArrayList<>();
        futureClasses = new ArrayList<>();
        teachers = new ArrayList<>();
        applicationData = new ApplicationData();
        userInfo = new UserInfoModel();
        res = getResources();
        token = new TokenModel();
    }

    public static MApplication getInstance() {
        return instance;
    }

    /**
     * быстрый доступ к ресурсам приложения
     */
    public Resources getMResources() {
        return res;
    }

    public List<AllDayClassModel> getFutureClasses() {
        return futureClasses;
    }

    public void setFutureClasses(List<AllDayClassModel> futureClasses) {
        this.futureClasses = futureClasses;
    }

    public ApplicationData getApplicationData() {
        return applicationData;
    }

    public void setApplicationData(ApplicationData applicationData) {
        this.applicationData = applicationData;
    }

    public TokenModel getToken() {
        return token;
    }

    public void setToken(TokenModel token) {
        this.token = token;
    }

    public List<AllDayClassModel> getAllClasses() {
        return allClasses;
    }

    public void setAllClasses(List<AllDayClassModel> allClasses) {
        this.allClasses = allClasses;
    }

    public UserInfoModel getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoModel userInfo) {
        this.userInfo = userInfo;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }
}
