from datetime import date
from typing import Optional
from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware
from pydantic import EmailStr
from redis_om import get_redis_connection, HashModel

app = FastAPI()

app.add_middleware(
    CORSMiddleware,
    allow_origins=['http://localhost:3000'],
    allow_methods=['*'],
    allow_headers=['*']
)

redisConnection = get_redis_connection(
     host="localhost",
     port="6379",
     decode_responses=True
)

# class Address():
#     address_line_1: str
#     address_line_2: Optional[str]
#     city: str # = Field(index=True)
#     state: str # = Field(index=True)
#     country: str
#     postal_code: str # = Field(index=True)

class User(HashModel):
    first_name: str # = Field(index=True)
    last_name: str # = Field(index=True)
    email: EmailStr # = Field(index=True)
    gender: str
    join_date: date
    date_of_birth: date
    bio: Optional[str]
    # address: Address

# Migrator(redisConnection).run()

@app.on_event("startup")
def startup():
    # You can set the Redis OM URL using the REDIS_OM_URL environment
    # variable, or by manually creating the connection using your model's
    # Meta object.
    User.Meta.database = redisConnection


@app.get('/users')
def all():
    pks =  User.all_pks()
    print(pks)
    return [formatList(pk) for pk in pks]

def formatList(pk: str):
    user = User.get(pk)
    return {
        'id': user.pk,
        'firstName': user.first_name,
        'lastName': user.last_name,
        'email': user.email,
        'gender': user.gender,
        'joinDate': user.join_date.isoformat(),
        'dateOfBirth': user.date_of_birth.isoformat()
    }

def formatDetails(user: User):
    return {
        'id': user.pk,
        'firstName': user.first_name,
        'lastName': user.last_name,
        'email': user.email,
        'gender': user.gender,
        'joinDate': user.join_date.isoformat(),
        'dateOfBirth': user.date_of_birth.isoformat(),
        'bio': user.bio,
        'address': user.address
    }

@app.post('/users')
def create(user: User):
    return user.save()


@app.get('/users/{pk}')
def get(pk: str):
    return formatDetails(User.get(pk))


@app.delete('/users/{pk}')
def delete(pk: str):
    return User.delete(pk)