package com.bytedance.praisedialoglib.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bytedance.praisedialoglib.R;
import com.bytedance.praisedialoglib.b;
/* loaded from: classes2.dex */
public class PraiseDialogActivity extends Activity {
    private RelativeLayout mBackground;
    private ImageView mCenterImg;
    private ImageView mCloseImageView;
    private TextView mComplainTv;
    private String mFrom = "";
    private boolean mIsUserClickBadOrGoodBtn = false;
    private TextView mPraiseTv;
    private TextView mSecondTitleTv;
    private TextView mTitleTv;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.praise_dialog);
        initView();
        handleIntent();
    }

    private void handleIntent() {
        Intent intent = getIntent();
        this.mFrom = intent.getStringExtra("from");
        if (intent.getIntExtra(b.BACKGROUND_RES, -1) != -1) {
            this.mBackground.setBackgroundResource(intent.getIntExtra(b.BACKGROUND_RES, -1));
        }
        if (intent.getIntExtra(b.MIDDLE_IMAGE_RES, -1) != -1) {
            this.mCenterImg.setBackgroundResource(intent.getIntExtra(b.MIDDLE_IMAGE_RES, -1));
        }
        if (!TextUtils.isEmpty(intent.getStringExtra(b.MAIN_TITLE_TEXT))) {
            this.mTitleTv.setText(intent.getStringExtra(b.MAIN_TITLE_TEXT));
        }
        if (!TextUtils.isEmpty(intent.getStringExtra(b.MAIN_TITLE_TEXT_COLOR))) {
            this.mTitleTv.setTextColor(Color.parseColor(intent.getStringExtra(b.MAIN_TITLE_TEXT_COLOR)));
        }
        if (intent.getIntExtra(b.MAIN_TITLE_TEXT_SIZE, -1) != -1) {
            this.mTitleTv.setTextSize(intent.getIntExtra(b.MAIN_TITLE_TEXT_SIZE, -1));
        }
        if (!TextUtils.isEmpty(intent.getStringExtra(b.SECOND_TITLE_TEXT))) {
            this.mSecondTitleTv.setText(intent.getStringExtra(b.SECOND_TITLE_TEXT));
        }
        if (!TextUtils.isEmpty(intent.getStringExtra(b.SECOND_TITLE_TEXT_COLOR))) {
            this.mSecondTitleTv.setTextColor(Color.parseColor(intent.getStringExtra(b.SECOND_TITLE_TEXT_COLOR)));
        }
        if (intent.getIntExtra(b.SECOND_TITLE_TEXT_SIZE, -1) != -1) {
            this.mSecondTitleTv.setTextSize(intent.getIntExtra(b.SECOND_TITLE_TEXT_SIZE, -1));
        }
        if (!TextUtils.isEmpty(intent.getStringExtra(b.NEGATIVE_BTN_TEXT))) {
            this.mComplainTv.setText(intent.getStringExtra(b.NEGATIVE_BTN_TEXT));
        }
        if (intent.getIntExtra(b.NEGATIVE_BTN_TEXT_SIZE, -1) != -1) {
            this.mComplainTv.setTextSize(intent.getIntExtra(b.NEGATIVE_BTN_TEXT_SIZE, -1));
        }
        if (!TextUtils.isEmpty(intent.getStringExtra(b.NEGATIVE_BTN_TEXT_COLOR))) {
            this.mComplainTv.setTextColor(Color.parseColor(intent.getStringExtra(b.NEGATIVE_BTN_TEXT_COLOR)));
        }
        if (intent.getIntExtra(b.NEGATIVE_BTN_TEXT_BG, -1) != -1) {
            this.mComplainTv.setBackgroundResource(intent.getIntExtra(b.NEGATIVE_BTN_TEXT_BG, -1));
        }
        if (!TextUtils.isEmpty(intent.getStringExtra(b.POSITIVE_BTN_TEXT))) {
            this.mPraiseTv.setText(intent.getStringExtra(b.POSITIVE_BTN_TEXT));
        }
        if (intent.getIntExtra(b.POSITIVE_BTN_TEXT_SIZE, -1) != -1) {
            this.mPraiseTv.setTextSize(intent.getIntExtra(b.POSITIVE_BTN_TEXT_SIZE, -1));
        }
        if (!TextUtils.isEmpty(intent.getStringExtra(b.POSITIVE_BTN_TEXT_COLOR))) {
            this.mPraiseTv.setTextColor(Color.parseColor(intent.getStringExtra(b.POSITIVE_BTN_TEXT_COLOR)));
        }
        if (intent.getIntExtra(b.POSITIVE_BTN_TEXT_BG, -1) != -1) {
            this.mPraiseTv.setBackgroundResource(intent.getIntExtra(b.POSITIVE_BTN_TEXT_BG, -1));
        }
    }

    private void initView() {
        this.mBackground = (RelativeLayout) findViewById(R.id.dialog_container);
        this.mTitleTv = (TextView) findViewById(R.id.market_feedback_dialog_title);
        this.mSecondTitleTv = (TextView) findViewById(R.id.market_feedback_dialog_tips);
        this.mComplainTv = (TextView) findViewById(R.id.tv_market_feedback_dialog_complaint);
        this.mPraiseTv = (TextView) findViewById(R.id.tv_market_feedback_dialog_praise);
        this.mCloseImageView = (ImageView) findViewById(R.id.market_feedback_dialog_close);
        this.mCenterImg = (ImageView) findViewById(R.id.market_feedback_dialog_center);
        this.mCloseImageView.setOnClickListener(new View.OnClickListener() { // from class: com.bytedance.praisedialoglib.ui.PraiseDialogActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PraiseDialogActivity.this.finish();
            }
        });
        this.mPraiseTv.setOnClickListener(new View.OnClickListener() { // from class: com.bytedance.praisedialoglib.ui.PraiseDialogActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                com.bytedance.praisedialoglib.utils.b.onEvent(com.bytedance.praisedialoglib.utils.b.EVENT_NAME_POP_GOOD, PraiseDialogActivity.this.mFrom);
                PraiseDialogActivity.this.mIsUserClickBadOrGoodBtn = true;
                PraiseDialogActivity.this.finish();
                com.bytedance.praisedialoglib.manager.b.getInstance().goToMarket();
            }
        });
        this.mComplainTv.setOnClickListener(new View.OnClickListener() { // from class: com.bytedance.praisedialoglib.ui.PraiseDialogActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                com.bytedance.praisedialoglib.utils.b.onEvent(com.bytedance.praisedialoglib.utils.b.EVENT_NAME_POP_BAD, PraiseDialogActivity.this.mFrom);
                PraiseDialogActivity.this.mIsUserClickBadOrGoodBtn = true;
                PraiseDialogActivity.this.finish();
                com.bytedance.praisedialoglib.manager.b.getInstance().goToAppFeedback();
            }
        });
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        com.bytedance.praisedialoglib.utils.b.onEvent(com.bytedance.praisedialoglib.utils.b.EVENT_NAME_POP_SHOW, this.mFrom);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        if (this.mIsUserClickBadOrGoodBtn) {
            com.bytedance.praisedialoglib.utils.b.onEvent(com.bytedance.praisedialoglib.utils.b.EVENT_NAME_POP_FORCE_CLOSE, this.mFrom);
        } else {
            com.bytedance.praisedialoglib.utils.b.onEvent(com.bytedance.praisedialoglib.utils.b.EVENT_NAME_POP_CLOSE, this.mFrom);
        }
        super.onDestroy();
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
    }

    @Override // android.app.Activity
    protected void onStart() {
        super.onStart();
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
    }
}
