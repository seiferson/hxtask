# hxtask
Task management app

## Get tasks
GET localhost:8080/api/v1/tasks
```
{
    "id": "6457bf251f44c9211f52a549",
    "title": "Add authorization to app",
    "description": "This is a small description of the task above in which key information can be noted",
    "owner": "cuherrer",
    "status": "created",
    "actions": [
        {
            "title": "Talk to POCs to understand the fix impact",
            "description": "What exactly you need to talk with the POC about?",
            "completed": null
        }
    ],
    "history": [
        {
            "date": "2023-05-07T15:07:22.192+00:00",
            "text": "codereview raised",
            "note": "Optional notes about the update"
        }
    ],
    "metadata": {
        "orareviewurl": "https://codereview.com/1123123",
        "bugno": "333345444",
        "customer": "X"
    }
}
```

## Create task
POST localhost:8080/api/v1/tasks
```
{
    "title" : "Add authorization to app",
    "description": "This is a small description of the task above in which key information can be noted",
    "owner": "cuherrer",
    "actions": [
        {
            "title": "Talk to POCs to understand the fix impact",
            "description": "What exactly you need to talk with the POC about?"
        }
    ],
    "history": [
        {
            "date": "2023-05-07T15:07:22.192+00:00",
            "text": "codereview raised",
            "note": "Optional notes about the update",
            "author": "cuherrer"
        }
    ],
    "metadata": {
        "bugno" : "333345444",
        "orareviewurl" : "https://codereview.com/1123123",
        "customer" : "X"
    }
}
```
