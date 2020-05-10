import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter/material.dart';
import 'package:team_swan_project_20200313/constants/color.dart';
import 'package:team_swan_project_20200313/constants/size.dart';
import 'package:team_swan_project_20200313/utils/profile_img_path.dart';
import 'package:team_swan_project_20200313/widgets/profile_side_menu.dart';

class ProfilePage extends StatefulWidget {
  @override
  _ProfilePageState createState() => _ProfilePageState();
}

/// SingleTickerProviderStateMixin는 어플리케이션에서 프레임 변화가 생길때마다 AnimationController에게 알려주어 에니메이션 변화를 수행할지 확인한다.
class _ProfilePageState extends State<ProfilePage> with SingleTickerProviderStateMixin{
  AnimationController _animationController;
  bool _menuOpened = false;
  double menuWidth;
  int duration = 300;
  AlignmentGeometry tabAlign = Alignment.centerRight;
  bool _tabIconGridSelected_userInfo = true;
  bool _tabIconGridSelected_groupInfo = false;
  bool _tabIconGridSelected_reportInfo = false;
  double _gridMagin_userInfo = 0;
  double _gridMagin_groupInfo = size.width;
  double _gridMagin_reportInfo = size.width * 2;
  GlobalKey<FormState> _formKeyUser = GlobalKey<FormState>();
  GlobalKey<FormState> _formKeyGroup = GlobalKey<FormState>();
  GlobalKey<FormState> _formKeyReport = GlobalKey<FormState>();
  TextEditingController _emailController = TextEditingController();
  TextEditingController _pwController = TextEditingController();
  TextEditingController _cpwController = TextEditingController();

  @override
  void initState(){
    _animationController = AnimationController(vsync: this, duration: Duration(milliseconds: duration));
    super.initState();
  }

  /// memory leak을 방지하기 위함
  @override
  void dispose(){
    _animationController.dispose();
    _emailController.dispose();
    _pwController.dispose();
    _cpwController.dispose();
    super.dispose();

  }

  @override
  Widget build(BuildContext context) {

    menuWidth = size.width / 1.5;
    return Scaffold(
      body: Stack(
        children: <Widget>[
          _profile(),
          _sideMenu(),
        ],
      ),
    );
  }

  Widget _sideMenu() {
    return AnimatedContainer(
      width: menuWidth,
      color: Colors.grey[200],
      curve: Curves.easeInOut,
      duration: Duration(microseconds: duration),
      transform: Matrix4.translationValues(
        _menuOpened ? size.width - menuWidth : size.width,
        0,
        0,
      ),
      child: SafeArea(
        child: SizedBox(
          width: menuWidth,
          child: ProfileSideMenu()
        ),
      ),
    );
  }

  Widget _profile() {
    return AnimatedContainer(
        color: Colors.transparent,
        curve: Curves.easeInOut,
        duration: Duration(milliseconds: duration),
        transform: Matrix4.translationValues(
          _menuOpened ? -menuWidth : 0,
          0,
          0,
        ),
        child: SafeArea(
          child: Column(
            children: <Widget>[
              _usernameIconButton(),
              Expanded(
                child: CustomScrollView(
                  /// List view 위젯을 안 쓰는 이유는 여러가지 그리드 위젯 같은것들을 shuffle 해서 사용 하기 위함이다.
                  slivers: <Widget>[
                    SliverList(
                      delegate: SliverChildListDelegate([
                        _getProfileHeader,
                        _userName(),
                        _userInfo(),
                        _getTabIconButtons,
                        _getAnimatedSelectedBar,
                      ]),
                    ),
                    _getImageGrid(context),
                  ],
                ),
              )
            ],
          ),
        ));
  }
  /// sliver가 아닌 위젯을 sliver로 만들어 주기 위해
  SliverToBoxAdapter _getImageGrid(BuildContext context) => SliverToBoxAdapter(
    child: Stack(
      children: <Widget>[
        AnimatedContainer(
          transform: Matrix4.translationValues(_gridMagin_userInfo, 0, 0),
          duration: Duration(milliseconds: duration),
          curve: Curves.easeInOut,
          height: size.height*0.5,
          width: size.width,
          child: _getUserInfoWidget(),
        ),
        AnimatedContainer(
          transform: Matrix4.translationValues(_gridMagin_groupInfo, 0, 0),
          duration: Duration(milliseconds: duration),
          curve: Curves.easeInOut,
          height: size.height*0.5,
          width: size.width,
          child: _getGroupInfoWidget(),
        ),
        AnimatedContainer(
          transform: Matrix4.translationValues(_gridMagin_reportInfo, 0, 0),
          duration: Duration(milliseconds: duration),
          curve: Curves.easeInOut,
          height: size.height*0.5,
          width: size.width,
          child: _getReportInfoWidget(),
        ),
      ],
    ),
  );

  Padding _userName() {
    return Padding(
      padding: const EdgeInsets.only(left: common_gap),
      child: Text(
        '김스완',
        style: TextStyle(fontWeight: FontWeight.w600, color: Colors.black),
      ),
    );
  }
  Padding _userInfo() {
    return Padding(
      padding: const EdgeInsets.only(left: common_gap),
      child: Text(
        'swan.kim@sk.com',
        style: TextStyle(fontWeight: FontWeight.w400),
      ),
    );
  }

  Row get _getProfileHeader => Row(
        /// dart 언어의 특징으로 별도의 getter, setter 메소드를 동일한 이름(파라)으로 불러오고 셋팅할 수 있다.
        children: <Widget>[
          Padding(
            padding: const EdgeInsets.all(common_gap),
            child: CircleAvatar(
              radius: 60,
              backgroundImage: NetworkImage(getProfileImgPath("happy")),
            ),
          ),
          Expanded(
            child: Table(
              children: [
                TableRow(children: [
                  _getStatusLabelWidget('Monitoring Info'),
                  _getStatusCircleWidget("off")
                ]),
                TableRow(children: [
                  _getStatusLabelWidget('Group Members'),
                  _getStatusCircleWidget("1")
                ]),
                TableRow(children: [
                  _getStatusLabelWidget('Report Members'),
                  _getStatusCircleWidget("2")
                ]),
              ],
            ),
          )
        ],
      );

  /// 텍스트가 늘어나서 가로 길이 등이 변경 하는 경우를 대비 해야 하는데 fittedbox 사용한다. 또는 모바일폰의 사이즈가 작은 경우를 대비해서 고정된 텍스트도 사용한다.
  Widget _getStatusLabelWidget(String value) => Center(
        child: Padding(
          padding: const EdgeInsets.symmetric(horizontal: common_s_gap, vertical: common_xs_gap),
          child: FittedBox(
              fit: BoxFit.scaleDown,
              child: Text(
                value,
                textAlign: TextAlign.center,
                style: TextStyle(fontWeight: FontWeight.w300, fontSize: 30),
              )),
        ),
      );

  Widget _getStatusCircleWidget(String value) => Center(
    child: Padding(
      padding: const EdgeInsets.symmetric(horizontal: common_s_gap, vertical: common_xs_gap),
      child: CircleAvatar(
        radius: 15,
        backgroundColor: main_color,
        child: Text(
          value,
          textAlign: TextAlign.center,
          style: TextStyle(fontWeight: FontWeight.w500, fontSize: 20, color: sub_color ),
        ),
      )
    ),
  );

  Widget _getUserInfoWidget() => Center(
      child: Padding(
        padding: const EdgeInsets.all(common_gap),
        child: Form(
          key: _formKeyUser,
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center, /// colum의 main 방향
            mainAxisSize: MainAxisSize.max,
            crossAxisAlignment: CrossAxisAlignment.stretch,
            children: <Widget>[
              TextFormField(
                controller: _emailController,
                decoration: getTextFieldDecor('Email'),
                validator: (String value){
                  if(value.isEmpty || !value.contains("@")){
                    return 'Please enter your email address!';
                  }
                  return null;
                },
              ),
              Spacer(
                flex: 1,
              ),
              TextFormField(
                obscureText: true,
                controller: _pwController,
                decoration: getTextFieldDecor('Password'),
                validator: (String value){
                  if(value.isEmpty){
                    return 'Please any password!';
                  }
                  return null;
                },
              ),
              Spacer(
                flex: 1,
              ),
              TextFormField(
                obscureText: true,
                controller: _cpwController,
                decoration: getTextFieldDecor('Confirm Password'),
                validator: (String value){
                  if(value.isEmpty || value != _pwController.value.text){
                    return 'Password does not match!';
                  }
                  return null;
                },
              ),
              Spacer(
                flex: 2,
              ),
              FlatButton(
                onPressed: (){
                  if(_formKeyUser.currentState.validate()){
                    //
                  }
                },
                child: Text(
                  "Join",
                  style: TextStyle(color: Colors.white),
                ),
                color: main_color,
                shape: RoundedRectangleBorder(
                  borderRadius: BorderRadius.circular(6),
                ),
                disabledColor: Colors.blue[100],
              ),
              Spacer(
                flex: 2,
              ),
            ],
          ),
        ),
      ),
  );

  Widget _getGroupInfoWidget() => Center(
    child: Padding(
      padding: const EdgeInsets.all(common_gap),
      child: Form(
        key: _formKeyGroup,
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center, /// colum의 main 방향
          mainAxisSize: MainAxisSize.max,
          crossAxisAlignment: CrossAxisAlignment.stretch,
          children: <Widget>[
            TextFormField(
              controller: _emailController,
              decoration: getTextFieldDecor('Email'),
              validator: (String value){
                if(value.isEmpty || !value.contains("@")){
                  return 'Please enter your email address!';
                }
                return null;
              },
            ),
            Spacer(
              flex: 1,
            ),
            TextFormField(
              obscureText: true,
              controller: _pwController,
              decoration: getTextFieldDecor('Password'),
              validator: (String value){
                if(value.isEmpty){
                  return 'Please any password!';
                }
                return null;
              },
            ),
            Spacer(
              flex: 1,
            ),
            TextFormField(
              obscureText: true,
              controller: _cpwController,
              decoration: getTextFieldDecor('Confirm Password'),
              validator: (String value){
                if(value.isEmpty || value != _pwController.value.text){
                  return 'Password does not match!';
                }
                return null;
              },
            ),
            Spacer(
              flex: 2,
            ),
            FlatButton(
              onPressed: (){
                if(_formKeyGroup.currentState.validate()){
                  //
                }
              },
              child: Text(
                "Join",
                style: TextStyle(color: Colors.white),
              ),
              color: main_color,
              shape: RoundedRectangleBorder(
                borderRadius: BorderRadius.circular(6),
              ),
              disabledColor: Colors.blue[100],
            ),
            Spacer(
              flex: 2,
            ),
          ],
        ),
      ),
    ),
  );

  Widget _getReportInfoWidget() => Center(
    child: Padding(
      padding: const EdgeInsets.all(common_gap),
      child: Form(
        key: _formKeyReport,
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center, /// colum의 main 방향
          mainAxisSize: MainAxisSize.max,
          crossAxisAlignment: CrossAxisAlignment.stretch,
          children: <Widget>[
            TextFormField(
              controller: _emailController,
              decoration: getTextFieldDecor('Email'),
              validator: (String value){
                if(value.isEmpty || !value.contains("@")){
                  return 'Please enter your email address!';
                }
                return null;
              },
            ),
            Spacer(
              flex: 1,
            ),
            TextFormField(
              obscureText: true,
              controller: _pwController,
              decoration: getTextFieldDecor('Password'),
              validator: (String value){
                if(value.isEmpty){
                  return 'Please any password!';
                }
                return null;
              },
            ),
            Spacer(
              flex: 1,
            ),
            TextFormField(
              obscureText: true,
              controller: _cpwController,
              decoration: getTextFieldDecor('Confirm Password'),
              validator: (String value){
                if(value.isEmpty || value != _pwController.value.text){
                  return 'Password does not match!';
                }
                return null;
              },
            ),
            Spacer(
              flex: 2,
            ),
            FlatButton(
              onPressed: (){
                if(_formKeyReport.currentState.validate()){
                  //
                }
              },
              child: Text(
                "Join",
                style: TextStyle(color: Colors.white),
              ),
              color: main_color,
              shape: RoundedRectangleBorder(
                borderRadius: BorderRadius.circular(6),
              ),
              disabledColor: Colors.blue[100],
            ),
            Spacer(
              flex: 2,
            ),
          ],
        ),
      ),
    ),
  );

  Row _usernameIconButton() {
    return Row(
      children: <Widget>[
        Expanded(
            child: Padding(
          padding: const EdgeInsets.only(left: common_gap),
          child: Text(
            'User Information Setting',
            style: TextStyle(fontWeight: FontWeight.bold, fontSize: 20),
          ),
        )),
        IconButton(
          icon: AnimatedIcon(
              icon: AnimatedIcons.menu_close,
              progress: _animationController,
              semanticLabel: 'Show menu',
          ),
          onPressed: () {
            _menuOpened ? _animationController.reverse() : _animationController.forward();
            setState(() {
              _menuOpened = !_menuOpened;
            });
          },
        )
      ],
    );
  }

  Widget get _getTabIconButtons => Row(
    children: <Widget>[
      Expanded(
        child: IconButton(
          icon: ImageIcon(AssetImage("assets/grid.png"), color: _tabIconGridSelected_userInfo?Colors.black:Colors.black26,),
          onPressed: (){
            _setTab("userInfo");
          },
        ),

      ),
      Expanded(
        child: IconButton(
          icon: ImageIcon(AssetImage("assets/saved.png"), color: _tabIconGridSelected_groupInfo?Colors.black:Colors.black26),
          onPressed: (){
            _setTab("groupInfo");
          },
        ),
      ),
      Expanded(
        child: IconButton(
          icon: ImageIcon(AssetImage("assets/grid.png"), color: _tabIconGridSelected_reportInfo?Colors.black:Colors.black26),
          onPressed: (){
            _setTab("reportInfo");
          },
        ),
      )
    ],
  );

  Widget get _getAnimatedSelectedBar => AnimatedContainer(
    alignment: tabAlign,
    duration: Duration(milliseconds: duration),
    curve: Curves.easeInOut,
    color: Colors.transparent,
    height: 1,
    width: size.width,
    child: Container(
      height: 1,
      width: size.width /3,
      color: Colors.black87,
    )
  );

  _setTab(String token){
    setState(() {
      if("userInfo" == token){
        this.tabAlign = Alignment.centerLeft;
        this._tabIconGridSelected_userInfo = true;
        this._tabIconGridSelected_groupInfo = false;
        this._tabIconGridSelected_reportInfo = false;
        this._gridMagin_userInfo = 0;
        this._gridMagin_groupInfo = -size.width;
        this._gridMagin_reportInfo = -size.width*2;
      }else if("groupInfo" == token){
        this.tabAlign = Alignment.center;
        this._tabIconGridSelected_userInfo = false;
        this._tabIconGridSelected_groupInfo = true;
        this._tabIconGridSelected_reportInfo = false;
        this._gridMagin_userInfo = -size.width;
        this._gridMagin_groupInfo = 0;
        this._gridMagin_reportInfo = -size.width*2;
      }else if("reportInfo" == token){
        this.tabAlign = Alignment.centerRight;
        this._tabIconGridSelected_userInfo = false;
        this._tabIconGridSelected_groupInfo = false;
        this._tabIconGridSelected_reportInfo = true;
        this._gridMagin_userInfo = -size.width*2;
        this._gridMagin_groupInfo = -size.width;
        this._gridMagin_reportInfo = 0;
      }
    });
  }

  InputDecoration getTextFieldDecor(String hint) {
    return InputDecoration(
        hintText: hint,
        enabledBorder: OutlineInputBorder(
          borderSide: BorderSide(
              color: Colors.grey[300],
              width: 1
          ),
          borderRadius: BorderRadius.circular(12),
        ),
        focusedBorder: OutlineInputBorder(
          borderSide: BorderSide(
              color: Colors.grey[300],
              width: 1
          ),
          borderRadius: BorderRadius.circular(12),
        ),
        fillColor: Colors.grey[100],
        filled: true
    );
  }

}