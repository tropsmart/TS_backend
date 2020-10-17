Feature: Post functionality

  Scenario Outline: As a customer I want to create a new post

    Given I can create a new post
    And I sending post to be created with post id <post_id>, title <post_title> and content <post_content>
    Then I should be able to see my newly created post
    Examples:
      | post_id | post_title | post_content |
      | 12345 | new_post | new_content |
