package com.jsonpen.jhjmoudle.ViewNeed;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jsonpen.jhjmoudle.Fragments.Fragment1;
import com.jsonpen.jhjmoudle.R;


/**
 * Created by 10398 on 2016/9/16.
 */
public class MenuButtonChooseView<T extends FragmentActivity> extends LinearLayout implements View.OnClickListener {

    public RelativeLayout rlOne;
    public RelativeLayout rlTwo;
    public RelativeLayout rlThree;
    public RelativeLayout rlFour;
    public RelativeLayout rlFive;
    private TextView tvOne;
    private TextView tvTwo;
    private TextView tvThree;
    private TextView tvMessage;
    private TextView tvFive;

    private FragmentTransaction fragmentTransaction;


    public int oldSelectedIndex = 0;

    private Fragment1 fragment1;
    private Fragment1 fragment2;
    private Fragment1 fragment3;
    private Fragment1 fragment4;
    private Fragment1 fragment5;
    private T mActivity;

    public MenuButtonChooseView(Context context) {
        this(context, null);
    }

    public MenuButtonChooseView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MenuButtonChooseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = View.inflate(getContext(), R.layout.main_button_redio_group, this);
        rlOne = (RelativeLayout) view.findViewById(R.id.rl_one);
        rlTwo = (RelativeLayout) view.findViewById(R.id.rl_two);
        rlThree = (RelativeLayout) view.findViewById(R.id.rl_three);
        rlFour = (RelativeLayout) view.findViewById(R.id.rl_four);
        rlFive = (RelativeLayout) view.findViewById(R.id.rl_five);

        rlOne.setOnClickListener(this);
        rlTwo.setOnClickListener(this);
        rlThree.setOnClickListener(this);
        rlFour.setOnClickListener(this);
        rlFive.setOnClickListener(this);

        tvOne = (TextView) view.findViewById(R.id.tv_one);
        tvTwo = (TextView) view.findViewById(R.id.tv_two);
        tvThree = (TextView) view.findViewById(R.id.tv_three);
        tvMessage = (TextView) view.findViewById(R.id.tv_message);
        tvFive = (TextView) view.findViewById(R.id.tv_five);

    }

    /**
     * 绑定viewpager
     */
    public void setFragmentList(T activity) {
        this.mActivity = activity;
    }


    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.rl_one) {
            reSet();
            tvOne.setSelected(true);
            oldSelectedIndex = 0;
            changeFragment(0);
        } else if (viewId == R.id.rl_two) {
            reSet();
            tvTwo.setSelected(true);
            oldSelectedIndex = 1;
            changeFragment(1);
        } else if (viewId == R.id.rl_three) {
            reSet();
            tvThree.setSelected(true);
            oldSelectedIndex = 2;
            changeFragment(2);
        } else if (viewId == R.id.rl_four) {
            reSet();
            tvMessage.setSelected(true);
            oldSelectedIndex = 3;
                /*tvMessage.setTextColor(getResources().getColor(R.color.red));*/
            changeFragment(3);
        } else if (viewId == R.id.rl_five) {
            reSet();
            tvFive.setSelected(true);
            oldSelectedIndex = 4;
            changeFragment(4);
        } else {
        }
    }

    private void changeFragment(int position) {
        fragmentTransaction = mActivity.getSupportFragmentManager().beginTransaction();
        hide(position, fragmentTransaction);

    }

    private void reSet() {
        tvOne.setSelected(false);
        tvTwo.setSelected(false);
        tvMessage.setSelected(false);
        tvFive.setSelected(false);
        tvThree.setSelected(false);
    }


    public void setPosition(int position) {
        View view = null;
        switch (position) {
            case 0:
                view = rlOne;
                break;
            case 1:
                view = rlTwo;
                break;
            case 2:
                view = rlThree;
                break;
            case 3:
                view = rlFour;
                break;
            case 4:
                view = rlFive;
                break;
        }
        onClick(view);

    }

    //用户端的隐藏
    private void hide(int i, FragmentTransaction transaction) {
        hideFragment(transaction);//0 1 2 3       4 1 2 7
        switch (i) {
            case 0:
                if (fragment1 == null) {
                    fragment1 = new Fragment1();
                    transaction.add(R.id.fl_content, fragment1);
                } else {
                    transaction.show(fragment1);
//                        QuestionFragment.reloadData();
                }

                break;
            case 1:
                if (fragment2 == null) {
                    fragment2 = new Fragment1();
                    transaction.add(R.id.fl_content, fragment2);
                } else {
                    transaction.show(fragment2);
//                        InvestmentFragment.reloadData();
                }
                  /*  InvestmentFragment = new InvestmentFragment();
                    transaction.add(R.id.layout_content, InvestmentFragment);*/
                break;
            case 2:
                if (fragment3 == null) {
//                        addMyFragment(transaction);
                    fragment3 = new Fragment1();
                    transaction.add(R.id.fl_content, fragment3);
                } else {
                    transaction.show(fragment3);
//                        fragment2.reloadData();
                }

                break;
            case 3:
                if (fragment4 == null) {
                    fragment4 = new Fragment1();
                    transaction.add(R.id.fl_content, fragment4);
                } else {
                    transaction.show(fragment4);
//                        fragment4.reloadData();
                }
                break;
            case 4:
                if (fragment5 == null) {
                    fragment5 = new Fragment1();
                    transaction.add(R.id.fl_content, fragment5);
                } else {
                    transaction.show(fragment5);
//                        fragment5.reloadData();
                }
                break;
        }

        transaction.commitAllowingStateLoss();

    }


    //隐藏fragment
    private void hideFragment(FragmentTransaction transaction) {

        if (fragment1 != null) {
            transaction.hide(fragment1);
        }

        if (fragment2 != null) {
            transaction.hide(fragment2);
        }
        if (fragment3 != null) {
            transaction.hide(fragment3);
        }

        if (fragment4 != null) {
            transaction.hide(fragment4);
        }


        if (fragment5 != null) {
            transaction.hide(fragment5);
        }


    }


}
