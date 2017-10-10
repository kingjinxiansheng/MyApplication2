package com.example.lenovo.myapplication;

import android.app.Fragment;
import android.graphics.Color;


import android.os.Bundle;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.example.lenovo.myapplication.R.id.home_tab;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private TabLayout homeTab;
    private ImageView jia;
    private LinearLayout layout;
    private LinearLayout mGridLayout_ll;
    private RelativeLayout mGridLayoutTitle;
    private MyGridLayout mGridLayout_dragable;
    private MyGridLayout mGridLayout2_disdragble;
    private boolean mFlag=true;
    private List<Fragment> list = new ArrayList<Fragment>();
    private ArrayList<String> list1;
    private ArrayList<String> list2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_news);
        initView();
        initData();
    }


    protected void initView() {
        viewPager = (ViewPager) findViewById(R.id.synthesize_home);
        homeTab = (TabLayout) findViewById(home_tab);
        homeTab.setVisibility(View.INVISIBLE);
        jia = (ImageView) findViewById(R.id.jia);
        layout = (LinearLayout) findViewById(R.id.layout);
        mGridLayout_ll = (LinearLayout)findViewById(R.id.gridlayout_ll);
        mGridLayoutTitle = (RelativeLayout) findViewById(R.id.layout_grid_title);
        mGridLayout_dragable = (MyGridLayout) findViewById(R.id.gridlayout_drageable);
        mGridLayout2_disdragble = (MyGridLayout) findViewById(R.id.gridlayout_undrageable);



    }


    protected void initData() {
//        BlankFragment blankFragment = new BlankFragment();
//        list.add(blankFragment);
//        list.add(blankFragment);
//        list.add(blankFragment);
//        list.add(blankFragment);
//        list.add(blankFragment);
//        String str[]={"开源资讯","最新博客","推荐博客","技术问答","每日一博"};
//        SyntheAdapter syntheAdapter = new SyntheAdapter(getSupportFragmentManager(),list,str);
//        viewPager.setAdapter(syntheAdapter);
//        homeTab.setTabMode(TabLayout.MODE_SCROLLABLE);
//        homeTab.setSelectedTabIndicatorColor(Color.GREEN);
//        homeTab.setTabTextColors(Color.GRAY, Color.GREEN);
//        homeTab.setupWithViewPager(viewPager);

        ///////////////////////////////////这个是显示的可拖拽标签的动画
        jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                if (mFlag) {
                    /*Intent intent=new Intent();
                    intent.putExtra("add",true);
                    intent.setAction("com.lusa");
                    getActivity().sendBroadcast(intent);*/
                    mGridLayout_ll .setVisibility(VISIBLE);
                    mGridLayoutTitle.setVisibility(VISIBLE);
                    homeTab.setVisibility(GONE);
                    // 加号旋转动画 打开 顺时针
                    Animation anim = AnimationUtils.loadAnimation(MainActivity.this,
                            R.anim.jiahao_shunshizhen_anim);
                    view.startAnimation(anim);

                    // 标题动画(由完全透明 ----完全不透明)
                    AlphaAnimation aa = new AlphaAnimation(0.0F, 1.0F);
                    aa.setDuration(500);
                    aa.setFillAfter(true);
                    mGridLayoutTitle.startAnimation(aa);

                    // 从上往下平移动画
                    Animation translateAnim = AnimationUtils.loadAnimation(
                            MainActivity.this, R.anim.top2bottom_anim);
                    mGridLayout_ll.startAnimation(translateAnim);

                    mFlag = false;
                } else {
                   /* Intent intent=new Intent();
                    intent.putExtra("ptt",true);
                    intent.setAction("com.lusa");
                    getActivity().sendBroadcast(intent);*/
                    // mGridLayoutTitle.setVisibility(GONE);
                    homeTab.setVisibility(VISIBLE);

                    // 逆时针旋转动画
                    Animation anim = AnimationUtils.loadAnimation( MainActivity.this,
                            R.anim.jiahao_nishi_anim);
                    view.startAnimation(anim);
                    // 标题动画(由完全不透明 ----完全透明)
                    AlphaAnimation aa = new AlphaAnimation(1.0F, 0.0F);
                    aa.setDuration(500);
                    aa.setFillAfter(true);
                    mGridLayoutTitle.startAnimation(aa);

                    // 从下往上平移动画
                    Animation translateAnim = AnimationUtils.loadAnimation(
                            MainActivity.this, R.anim.bottom2top_anim);
                    mGridLayout_ll.startAnimation(translateAnim);
                    translateAnim.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            mGridLayout_ll.setVisibility(GONE);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    mFlag = true;
                }
            }
        });
        //初始化gridViewlayout
        initGridLayout();
        //点击下部，移除一个item在第一个里面添加一个item
        mGridLayout2_disdragble.setOnItemClickLitener(new  MyGridLayout.OnItemClickLitener() {
            @Override
            public void OnItemClickLitener(String strItem, View v) {
                mGridLayout2_disdragble.removeView(v);
                mGridLayout_dragable.addTvItem(strItem);
            }


        });
//点击上部，移除一个item在第二个里面添加一个item
        mGridLayout_dragable.setOnItemClickLitener(new MyGridLayout.OnItemClickLitener() {
            @Override
            public void OnItemClickLitener(String strItem, View v) {

                mGridLayout_dragable.removeView(v);
                mGridLayout2_disdragble.addTvItem(strItem);

            }
        });

    }




    private void initGridLayout() {
        mGridLayout_dragable.setGridLayoutItemDrageAble(true);
        list1 = new ArrayList<String>();
        list1.add("开源资讯");
        list1.add("推荐博客");
        list1.add("技术问答");
        list1.add("每日一博");
        list1.add("编程语言");
        list1.add("软件工程");
        list1.add("云计算");
        list1.add("开源访谈");

        mGridLayout_dragable.addItems(list1);

        mGridLayout2_disdragble.setGridLayoutItemDrageAble(false);
        list2 = new ArrayList<String>();
        list2.add("站务建议");
        list2.add("职业生涯");
        list2.add("技术问答");
        list2.add("前端开发");
        list2.add("技术分享");
        list2.add("软件工程");
        list2.add("最新软件");
        list2.add("高手问答");
        list2.add("开源硬件");
        list2.add("移动开发");
        list2.add("码云推荐");
        mGridLayout2_disdragble.addItems(list2);
    }

}
