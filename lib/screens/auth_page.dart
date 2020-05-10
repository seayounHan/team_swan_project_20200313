import 'package:flutter/material.dart';
import 'package:team_swan_project_20200313/widgets/sign_in_form.dart';
import 'package:team_swan_project_20200313/widgets/sign_up_form.dart';
import 'package:team_swan_project_20200313/constants/color.dart';

class AuthPage extends StatefulWidget {
  @override
  _AuthPageState createState() => _AuthPageState();
}

class _AuthPageState extends State<AuthPage> {

  Widget currentWidget = SignInForm();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      resizeToAvoidBottomInset: false, /// 키보드가 올라와도 화면을 리사이즈 하지 말아라.ㄴ
      body: SafeArea(
        child: Stack(
          children: <Widget>[
            AnimatedSwitcher(
                duration: Duration(seconds: 1),
                child: currentWidget),
            _goToSignUpPageBtn(context),
          ],
        ),
      ),
    );
  }

  Positioned _goToSignUpPageBtn(BuildContext context){
    return Positioned(
      left: 0,
      right: 0,
      bottom: 0,
      height: 40,
      child: FlatButton(
        shape: Border(top: BorderSide(color: Colors.grey[300])),
        onPressed: (){
          setState(() {
            if(currentWidget is SignInForm){
              currentWidget = SignUpForm();
            }else{
              currentWidget = SignInForm();
            }
          });
        },
        child: RichText(
          textAlign: TextAlign.center,
          text: TextSpan(
              style: const TextStyle(), /// 기본값으로 넣어주어야 한다.
              children: <TextSpan>[
                TextSpan(
                    text: (currentWidget is SignInForm)?"Don't have and account?":"Already have an account?",
                    style: TextStyle(
                        fontWeight: FontWeight.w300,
                        color: Colors.black54
                    )
                ),
                TextSpan(
                    text: (currentWidget is SignInForm)?"Sign UP":"  Sign in",
                    style: TextStyle(
                        fontWeight: FontWeight.bold,
                        color: Colors.blue
                    )
                ),
              ]
          ),
        ),
      ),
    )
    ;
  }
}