package com.hu.microserviceuser.functionaltesting.stepdefinition;

import com.hu.microserviceuser.data.entity.User;
import com.hu.microserviceuser.web.dto.UserDto;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.var;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class UserDefStep extends AbstractStepDef {

    @Given("I have a user {string}")
    public void iHaveAUser(String username){
        testContext().reset();
        User user = new User(null, username, "firstname", "lastname");
        testContext().setPayload(user);
    }

    @When("I try and POST the user")
    public void iPostUser(){
        String url = "/users/";
        executePost(url);
    }

    @Then("I can GET the user")
    public void iGetUsers(List<String> holders) {
        executeGet("/users/");
        final var response = testContext().getResponse()
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .body()
                .jsonPath()
                .getList(".", UserDto.class);
        assertThat(response)
                .extracting(UserDto::getUsername)
                .containsExactlyElementsOf(holders);
    }
}
