package com.zyf.viewlibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


/**
 * 包含一个输入框和左右两个按钮的布局
 */
public class HorizontalEditLayout extends LinearLayout {

    public interface ImageClickListener{
        void onImageClick(boolean isLeftImage);
    }

    private ImageView leftImageView;
    private EditText centerEditText;
    private ImageView rightImageView;

    @Nullable
    private ImageClickListener imageClickListener;


    public HorizontalEditLayout(Context context) {
        this(context, null);
    }

    public HorizontalEditLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HorizontalEditLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setOrientation(HORIZONTAL);
        this.setGravity(Gravity.CENTER);
        try (final TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.HorizontalEditLayout)){
            final int leftImageWidth = typedArray.getDimensionPixelSize(R.styleable.HorizontalEditLayout_left_image_width, 0);
            final int leftImageHeight = typedArray.getDimensionPixelSize(R.styleable.HorizontalEditLayout_left_image_height, 0);
            final int rightImageWidth = typedArray.getDimensionPixelSize(R.styleable.HorizontalEditLayout_right_image_width, 0);
            final int rightImageHeight = typedArray.getDimensionPixelSize(R.styleable.HorizontalEditLayout_right_image_height, 0);
            final Drawable leftIcon = typedArray.getDrawable(R.styleable.HorizontalEditLayout_left_image_src);
            final Drawable rightIcon = typedArray.getDrawable(R.styleable.HorizontalEditLayout_right_image_src);
            final String editTextHint = typedArray.getString(R.styleable.HorizontalEditLayout_edit_hint_text);
            final int editTextHintColor = typedArray.getColor(R.styleable.HorizontalEditLayout_edit_hint_text_color, Color.GRAY);
            final boolean editTextSingleLine = typedArray.getBoolean(R.styleable.HorizontalEditLayout_edit_text_single_line, true);
            final int editTextSize = typedArray.getDimensionPixelSize(R.styleable.HorizontalEditLayout_edit_text_size,getResources().getDimensionPixelSize(R.dimen.sp_14));
            final int editTextColor = typedArray.getColor(R.styleable.HorizontalEditLayout_edit_text_color, Color.BLACK);
            final int editMarginLeftIcon = typedArray.getDimensionPixelSize(R.styleable.HorizontalEditLayout_edit_text_margin_left_icon, 0);
            final int editMarginRightIcon = typedArray.getDimensionPixelSize(R.styleable.HorizontalEditLayout_edit_text_margin_right_icon, 0);
            final int leftImagePaddingStart = typedArray.getDimensionPixelSize(R.styleable.HorizontalEditLayout_left_image_padding_start,0);
            final int leftImagePaddingEnd = typedArray.getDimensionPixelSize(R.styleable.HorizontalEditLayout_left_image_padding_end,0);
            final int leftImagePaddingTop = typedArray.getDimensionPixelSize(R.styleable.HorizontalEditLayout_left_image_padding_top,0);
            final int leftImagePaddingBottom = typedArray.getDimensionPixelSize(R.styleable.HorizontalEditLayout_left_image_padding_bottom,0);
            final int rightImagePaddingStart = typedArray.getDimensionPixelSize(R.styleable.HorizontalEditLayout_right_image_padding_start,0);
            final int rightImagePaddingEnd = typedArray.getDimensionPixelSize(R.styleable.HorizontalEditLayout_right_image_padding_end,0);
            final int rightImagePaddingTop = typedArray.getDimensionPixelSize(R.styleable.HorizontalEditLayout_right_image_padding_top,0);
            final int rightImagePaddingBottom = typedArray.getDimensionPixelSize(R.styleable.HorizontalEditLayout_right_image_padding_bottom,0);
            final int editTextInputType = typedArray.getInt(R.styleable.HorizontalEditLayout_android_inputType, 0);
            initLeftImageView(leftImageWidth,leftImageHeight, leftIcon,leftImagePaddingStart,leftImagePaddingEnd,leftImagePaddingTop,leftImagePaddingBottom);
            initCenterEditText(editTextHint, editTextHintColor, editTextSingleLine, editTextSize, editTextColor, editMarginLeftIcon, editMarginRightIcon, editTextInputType);
            initRightImageView(rightImageWidth,rightImageHeight, rightIcon, rightImagePaddingStart,rightImagePaddingEnd,rightImagePaddingTop,rightImagePaddingBottom);
        }
    }

    private void initLeftImageView(int width, int height, Drawable drawable, int paddingStart, int paddingEnd, int paddingTop, int paddingBottom){
        leftImageView = new ImageView(getContext());
        leftImageView.setImageDrawable(drawable);
        leftImageView.setPadding(paddingStart,paddingTop,paddingEnd,paddingBottom);
        leftImageView.setOnClickListener(v -> {
            if (imageClickListener != null){
                imageClickListener.onImageClick(true);
            }
        });
        final LayoutParams params = new LayoutParams(width, height);
        this.addView(leftImageView, params);
    }

    private void initCenterEditText(String hintText, int hintTextColor, boolean singleLine, int textSize, int textColor, int marginLeft, int marginRight, int inputType){
        centerEditText = new EditText(getContext());
        centerEditText.setBackground(null);
        centerEditText.setHint(hintText);
        centerEditText.setHintTextColor(hintTextColor);
        centerEditText.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        centerEditText.setTextColor(textColor);
        if (singleLine){
            centerEditText.setSingleLine(true);
            centerEditText.setLines(1);
        }
        centerEditText.setPadding(0,0,0,0);
        if (inputType != 0) {
            final Typeface originalTypeface = centerEditText.getTypeface();
            centerEditText.setInputType(inputType);
            centerEditText.setTypeface(originalTypeface);
        }
        final LayoutParams params = new LayoutParams(0, LayoutParams.WRAP_CONTENT);
        params.leftMargin = marginLeft;
        params.rightMargin = marginRight;
        params.weight = 1f;
        this.addView(centerEditText, params);
    }

    private void initRightImageView(int width, int height, Drawable drawable, int paddingStart, int paddingEnd, int paddingTop, int paddingBottom){
        rightImageView = new ImageView(getContext());
        rightImageView.setImageDrawable(drawable);
        rightImageView.setPadding(paddingStart,paddingTop,paddingEnd,paddingBottom);
        rightImageView.setOnClickListener(v -> {
            if (imageClickListener != null){
                imageClickListener.onImageClick(false);
            }
        });
        final LayoutParams params = new LayoutParams(width,height);
        this.addView(rightImageView, params);
    }

//    @Nullable
//    public ImageFilterView getLeftImageView(){
//        return leftImageView;
//    }
//
//    @Nullable
//    public ImageFilterView getRightImageView(){
//        return rightImageView;
//    }
//
//    @Nullable
//    public EditText getCenterEditText(){
//        return centerEditText;
//    }


    public void setImageClickListener(@Nullable ImageClickListener listener){
        this.imageClickListener = listener;
    }

    @NonNull
    public String getInputContent() {
        if (centerEditText != null) {
            final String inputContent = centerEditText.getText().toString();
            return inputContent;
        }
        return "";
    }


    public int getInputType(){
        if (centerEditText != null){
            return centerEditText.getInputType();
        }
        return 0;
    }


    public void updateLeftImageDrawable(@Nullable Drawable drawable){
        if (leftImageView != null){
            leftImageView.setImageDrawable(drawable);
        }
    }

    public void updateRightImageDrawable(@Nullable Drawable drawable){
        if (rightImageView != null){
            rightImageView.setImageDrawable(drawable);
        }
    }

    public void updateCenterEditInputType(int inputType){
        if (centerEditText != null){
            final Typeface originalTypeface = centerEditText.getTypeface();
            centerEditText.setInputType(inputType);
            centerEditText.setSelection(centerEditText.getText().toString().length());
            centerEditText.setTypeface(originalTypeface);
        }
    }

    public void updateEditContent(String content){
        if (centerEditText != null){
            centerEditText.setText(content);
        }
    }
}
