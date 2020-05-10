import 'package:flutter/material.dart';
import 'package:team_swan_project_20200313/constants/size.dart';
import 'package:team_swan_project_20200313/screens/auth_page.dart';

class ProfileSideMenu extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      decoration: BoxDecoration(
        border: Border(left: BorderSide(color: Colors.grey[300])),
      ),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          Padding(
            padding: const EdgeInsets.all(common_gap),
            child: Text(
              'Settings',
              style: TextStyle(color: Colors.black87, fontWeight: FontWeight.bold),

            ),
          ),
          Container(
            color: Colors.grey[300],
            height: 1,
          ),
          FlatButton.icon(
              onPressed: (){
                final route = MaterialPageRoute(builder: (context)=>AuthPage());
                Navigator.pushReplacement(context,route);/// pushreplacement 말고 push를 쓰게 되면 뒤에 기존 화면이 남게 된다.
              },
              icon: Icon(Icons.exit_to_app),
              label: Text(
                'Log out',
                style: TextStyle(
                    color: Colors.black87, fontWeight: FontWeight.w500
                ),
              )
          )
        ],
      ),
    );
  }
}