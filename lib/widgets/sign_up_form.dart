
import 'package:flutter/material.dart';
import 'package:team_swan_project_20200313/constants/color.dart';
import 'package:team_swan_project_20200313/constants/size.dart';
import 'package:team_swan_project_20200313/main_page.dart';
import 'package:team_swan_project_20200313/utils/simple_snack_bar.dart';

class SignUpForm extends StatefulWidget {
  @override
  _SignUpFormState createState() => _SignUpFormState();
}

class _SignUpFormState extends State<SignUpForm> {

  GlobalKey<FormState> _formKey = GlobalKey<FormState>();
  TextEditingController _emailController = TextEditingController();
  TextEditingController _pwController = TextEditingController();
  TextEditingController _cpwController = TextEditingController();

  @override
  void dispose() {
    _emailController.dispose();
    _pwController.dispose();
    _cpwController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      resizeToAvoidBottomInset: true,
      body: Padding(
        padding: const EdgeInsets.all(common_gap),
        child: Form(
          key: _formKey,
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center, /// colum의 main 방향
            mainAxisSize: MainAxisSize.max,
            crossAxisAlignment: CrossAxisAlignment.stretch,
            children: <Widget>[
              Spacer(
                flex: 6,
              ),
              Image.asset("assets/swan_logo.png"),
              Spacer(
                flex: 1,
              ),
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
                  if(_formKey.currentState.validate()){
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
              Stack(
                alignment: Alignment.center,
                children: <Widget>[
                  Positioned(
                    left: 0,
                    right: 0,
                    height: 1,
                    child: Container(
                      color: Colors.grey[300],
                      height: 1,
                    ),
                  ),
                  Container(
                    height: 3,
                    width: 50,
                    color: Colors.grey[50],
                  ),
                  Text(
                    'Info',
                    style: TextStyle(
                        color: Colors.grey, fontWeight: FontWeight.bold
                    ),
                  ),
                ],
              ),
              Spacer(
                flex: 2,
              ),
              FlatButton(
                textColor: Colors.blue,
                onPressed: () {
                  simpleSnackbar(context, 'coming soon');
                },
                child: Text(
                  "This application was created for the purpose of responding to EMS. Click here for more information.",
                  style: TextStyle(color: sub_color),
                ),
              ),
              Spacer(
                flex: 2,
              ),
              Spacer(
                flex: 6,
              )
            ],

          ),
        ),
      ),

    );
  }

  InputDecoration getTextFieldDecor(String hint){
    return InputDecoration(
        hintText: hint,
        enabledBorder: OutlineInputBorder(
          borderSide: BorderSide(
              color: Colors.grey[300],
              width: 1
          ),
          borderRadius: BorderRadius.circular(12),
        ),
        focusedBorder:OutlineInputBorder(
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
