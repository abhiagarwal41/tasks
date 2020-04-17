Feature: Test VWO

  @test
  Scenario: Verify happy path
    Given I start application
    And I Wait for "home_page" to load
    And I Wait until "view_heatmap" is visible
    When I click on "view_heatmap"
    Then Verify that new tab is opened
    When I switch to second tab
    And I Wait for "heatmap_page" to load
    And I switch to iframe "heatmap_iframe"
    And I click on "element_list"
    When I switch to default content
    And I switch to iframe "element_list_iframe"
    Then Verify that "element_list_panel" should be visible
    When I click on "start_free_trial_element"
    And I switch to default content
    Then I Wait until "start_free_trial_button_border" is visible
    And I close browser
