package controllers.validators;

import models.User;
import play.data.validation.Check;

public class UserExist extends Check {

	@Override
	public boolean isSatisfied(Object user, Object email) {
		User oldUser = User.find("byEmail", ((User)user).email).first();
        if (oldUser == null){
            return true;
        }else{
            return false;
        }
	}

}
