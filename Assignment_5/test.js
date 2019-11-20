require('./data/db')();

const uniDao = require('./data/daos/university.dao.server');
const assert = require('assert');

// 1.
truncateDatabase = () => uniDao.truncateDatabase();

// 2. 
populateDatabase = () => uniDao.populateDatabase();

// 3.
testStudentsInitialCount = () => 
    uniDao.findAllStudents()
        .then(students => assert(students.length == 2))
        .catch(err => console.log(err.message));

// 4.
testQuestionsInitialCount = () => 
    uniDao.findAllQuestions()
        .then(questions => assert(questions.length == 4))
        .catch(err => console.log(err.message));

// 5.
testAnswersInitialCount = () =>
    uniDao.findAllAnswers()
        .then(answers => assert(answers.length == 8))
        .catch(err => console.log(err.message));

// 6.
testDeleteAnswer = () => 
    uniDao.deleteAnswer(890)
        .then(() => uniDao.findAllAnswers())
        .then(answers => assert(answers.length == 7))
        .then(() => uniDao.findAnswersByStudent(234))
        .then(answers => assert(answers.length == 3))
        .catch(err => console.log(err.message));

// 7.
testDeleteQuestion = () =>
    uniDao.deleteQuestion(321)
        .then(() => uniDao.findAllQuestions())
        .then(questions => assert(questions.length == 3))
        .catch(err => console.log(err.message));

// 8.
testDeleteStudent = () =>
    uniDao.deleteStudent(234)
        .then(() => uniDao.findAllStudents())
        .then(students => assert(students.length == 1))
        .catch(err => console.log(err.message));

truncateDatabase()
    .then(() => populateDatabase())
    .then(() => testStudentsInitialCount())
    .then(() => testQuestionsInitialCount())
    .then(() => testAnswersInitialCount())
    .then(() => testDeleteAnswer())
    .then(() => testDeleteQuestion())
    .then(() => testDeleteStudent())
    .then(() => console.log("All tests are correct!"))
    .then(() => require('mongoose').connection.close());