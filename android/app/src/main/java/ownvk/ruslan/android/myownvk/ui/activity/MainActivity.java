package ownvk.ruslan.android.myownvk.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;
import com.vk.sdk.util.VKUtil;

import java.util.Arrays;

import ownvk.ruslan.android.myownvk.CurrentUser;
import ownvk.ruslan.android.myownvk.R;
import ownvk.ruslan.android.myownvk.VkApplication;
import ownvk.ruslan.android.myownvk.common.MyFragmentManager;
import ownvk.ruslan.android.myownvk.consts.ApiConstants;
import ownvk.ruslan.android.myownvk.mvp.presenter.MainPresenter;
import ownvk.ruslan.android.myownvk.mvp.view.MainView;
import ownvk.ruslan.android.myownvk.ui.fragment.NewsFeedFragment;

public class MainActivity extends BaseActivity implements MainView {

    @InjectPresenter
    MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        VkApplication.getApplicationComponent().inject(this);

//        String[] fingerprints = VKUtil.getCertificateFingerprint(this, this.getPackageName());
//        VKSdk.login(this,ApiConstants.DEFAULT_LOGIN_SCOPE);

        mPresenter.checkAuth();
    }

    @Override
    protected int getMainContentLayout() {
        return R.layout.activity_main;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
//
                mPresenter.checkAuth();
            }
            @Override
            public void onError(VKError error) {
// Произошла ошибка авторизации (например, пользователь запретил авторизацию)
            }
        })) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void startSignIn() {
        VKSdk.login(this,ApiConstants.DEFAULT_LOGIN_SCOPE);
    }

    @Override
    public void signedId() {
        Toast.makeText(getBaseContext(),"Current user id:"+ CurrentUser.getId(),Toast.LENGTH_LONG).show();
        setContent(new NewsFeedFragment());
    }
}
