The objective of this application is to provide user capability to manage Multi ToDo Lists along with given Items.
 When application is launched, user  will be provided with a blank where he/she can create multiple ToDolIsts
 from scratch or import the existing ones saved to the external storage,
  Following below depicts the requirements of this application and how it's being implemented in the User Interface:

  Manage Multiple Lists View:

  1>A user shall be able to add a new todo list - This function is fulfilled by clicking the Add Button . Add List Form
  opens up below, User can add the Title and click submit. New ToDolIst will appear in the "ToDo Lists" Table View
  2> A todo list shall have a title - User wil see the Title of each ToDo List in the "ToDo Lists" Table View.
  Ability to Add/Edit Title IS PART OF REQUIREMENT 1 AND 4
  3>A user shall be able to remove an existing todo list - This function is fulfilled by clicking the Delete Button
   When Delete button is pressed, the ToDoList in context will get deleted from the ToDo Lists View
  4>A user shall be able to edit the title of an existing todo list- This function is fulfilled by clicking the
     Edit Button  of th give ToDo List . Edit List Form opens up below, User can Edit the Title and click submit.
     ToDolIst updated Title will appear in the "ToDo Lists" Table View for a given ToDoList
  5> The application shall be able to manage at least 100 unique todo lists: When ever Add Button is clicked,
  application checks to see if there are already 100 ToDo Lists already. If Yes suer will get a message stating
  "No more ToDo Lists" can be created. On Top of that application also compares the newly added List Title or
  Edit List Title for uniqueness at the time of committing

  6> Ability to See and Manage Items of a given ToDo List - This is achieved by clicking the Manage Items Button.
  Once Clicked the Single "ToDo List" Form will appear.
  7>A user shall be able to save all the items across all the todo lists to external storage - This is accomplished
   by clicking the "Export Lists" button. Once clicked all the TodLists along with its item will be written to external
   storage file
  8>A user shall be able to load multiple todo lists that were previous saved to external storage -- This is achieved
  by click the "Import Lists" button. Once clicked the ToDoLists that was preciously save as part of requirement 8 above
  will reappear in the "ToDo Lists" Table View

   9>A user shall be able to load a single todo list that was previously saved to external storage -
   This is achieved by click the "Import List" button. Once clicked the ToDoList that was preciously saved as part of
   requirement 10 of the "Manage Single Lists" View below will reappear in the "ToDo Lists" Table View



  Manage Single Lists View:
  1>A user shall be able to display all the existing items in a todo list: This is achieved by clicking the Manage Items
  button on the "Manage Multiple Lists View:. All the Items of a given ToDolIST will be displayed in the "ToDo List"
  Table View
  2>A user shall be able to display only the incomplete items in a todo list: This is achieved by selecting the
  "In Progress" option from the Status dropdown
  3>A user shall be able to display only the completed items in a todo list: his is achieved by selecting the
     "Completed" option from the dropdown

  4>A user shall be able to add a new item to an existing todo list":This function is fulfilled by clicking the Add Button
   Add Item Form opens up below, User can add the Title,Description,Due date and Status  and click submit.
   New Item  will appear in the "ToDo List" Table View
   5>
  5>A todo list shall have the capacity to store at least 100 unique items:When ever Add Button is clicked,
    application checks to see if there are already 100 Items already exist. If Yes user will get a message stating
    "No more ToDo Items" can be created. On Top of that application also compares the newly added Item Title or
     Edit Item Title for uniqueness at the time of committing


  6>A user shall be able to delete an item from an existing todo list:This function is fulfilled by clicking the
    Delete Button. When Delete button is pressed, the Item  in context will get deleted from the ToDo List Table View
  7>An item shall have a description: This is achieved as part of requirement 4
 8>An item shall have a due date, formatted as YYYY-MM-DD: This is achieved as part of requirement 4
  7>A user shall be able to edit the description of an item within an existing todo list:This is achieved by clicking
  the Edit Button. Once clicked the Item Form shows up at the button, User can edit the Description and submit.New
  description will get reflected in the "tOdO List Items"   Table View
  9>A user shall be able to edit the due date of an item within an existing todo list:This is achieved by clicking
   the Edit Button. Once clicked the Item Form shows up at the button, User can edit the Due Date and submit.
   New Due Date will get reflected in the "tOdO List Items"   Table View
  10>A user shall be able to mark an item in a todo list as complete:This is achieved by clicking
    the Edit Button. Once clicked the Item Form shows up at the button, User can Select the "Completed" option of the
    Status dropdown and submit.Completed Status  will get reflected in the "tOdO List Items"   Table View
  11>A user shall be able to save all the items in a single todo list to external storage:This is accomplished
     by clicking the "Export List" button. Once clicked all the Items of the given TodO lIST will be written to
     the external  storage file













