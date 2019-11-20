const studentModel = require('../models/student.model.server');
const questionModel = require('../models/question.model.server');
const answerModel = require('../models/answer.model.server');
const quizWidgetModel = require('../models/quiz-widget.model.server');

// 1.
truncateDatabase = () => {
    return answerModel.remove()
        //.then(console.log(1))
        .then(() => studentModel.remove())
        //.then(console.log(2))
        .then(() => questionModel.remove());
        //.then(console.log(3));
        // .then(quizWidgetModel.remove())
        //.catch(err => console.log(err.message));
};

// 2.
populateDatabase = () => {
    
    // Create students
    return studentModel.create({
            _id: 123,
            username: "alice",
            password: "alice",
            firstName: "Alice",
            lastName: "Wonderland",
            gradYear: 2020,
            scholarship: 15000
        })
        .then (() =>
            studentModel.create({
                _id: 234,
                username: "bob",
                password: "bob",
                firstName: "Bob",
                lastName: "Hope",
                gradYear: 2021,
                scholarship: 12000
            })
        )

    // Create questions
        .then (() =>
            questionModel.create({
                _id: 321,
                question : "Is the following schema valid?",
                points : 10,
                questionType : "TRUE_FALSE",
                trueFalse : {isTrue: false}
            })
        )
        .then (() =>
            questionModel.create({
                _id: 432,
                question : "DAO stands for Dynamic Access Object.",
                points : 10,
                questionType : "TRUE_FALSE",
                trueFalse : {isTrue: false}
            })
        )

        .then (() =>
            questionModel.create({
                _id: 543,
                question : "What does JPA stand for?",
                points : 10,
                questionType : "MULTIPLE_CHOICE",
                multipleChoice : {
                    choices: "Java Persistence API,Java Persisted Application,JavaScript Persistence API,JSON Persistent Associations",
                    correct: 1
                }
            })
        )

        .then (() =>
            questionModel.create({
                _id: 654,
                question : "What does ORM stand for?",
                points : 10,
                questionType : "MULTIPLE_CHOICE",
                multipleChoice : {
                    choices: "Object Relational Model,Object Relative Markup,Object Reflexive Model,Object Relational Mapping",
                    correct: 4
                }
            })
        )

    // Create answers
        .then (() =>
            answerModel.create({
                _id: 123,
                trueFalseAnswer: true,
                student: 123,
                question: 321
            })
        )

        .then (() =>
            answerModel.create({
                _id: 234,
                trueFalseAnswer: false,
                student: 123,
                question: 432
            })
        )

        .then (() =>
            answerModel.create({
                _id: 345,
                multipleChoiceAnswer: 1,
                student: 123,
                question: 543
            })
        )

        .then (() =>
            answerModel.create({
                _id: 456,
                multipleChoiceAnswer: 2,
                student: 123,
                question: 654
            })
        )

        .then (() =>
            answerModel.create({
                _id: 567,
                trueFalseAnswer: false,
                student: 234,
                question: 321
            })
        )

        .then (() =>
            answerModel.create({
                _id: 678,
                trueFalseAnswer: true,
                student: 234,
                question: 432
            })
        )

        .then (() =>
            answerModel.create({
                _id: 789,
                multipleChoiceAnswer: 3,
                student: 234,
                question: 543
            })
        )

        .then (() =>
            answerModel.create({
                _id: 890,
                multipleChoiceAnswer: 4,
                student: 234,
                question: 654
            })
        )
        
        .catch(err => console.log(err.message));
};

// 3.
createStudent = student => studentModel.create(student);

// 4.
deleteStudent = studentId => studentModel.remove({_id: studentId});

// 5.
createQuestion = question => questionModel.create(question);

// 6.
deleteQuestion = questionId => questionModel.remove({_id: questionId});

// 7.
answerQuestion = (studentId, questionId, answer) => {
    answer.student = studentId;
    answer.question = questionId;
    return answerModel.create(answer);
};

// 8. 
deleteAnswer = answerId => answerModel.remove({_id: answerId});

// finder methods
// 1.
findAllStudents = () => studentModel.find();

// 2.
findStudentById = studentId => studentModel.findById(studentId);

// 3.
findAllQuestions = () => questionModel.find();

// 4.
findQuestionById = questionId => questionModel.find({_id: questionId});

// 5.
findAllAnswers = () => answerModel.find();

// 6.
findAnswerById = answerId =>answerModel.find({_id: answerId});

// 7.
findAnswersByStudent = studentId => answerModel.find({student: studentId});

// 8.
findAnswersByQuestion = questionId => answerModel.find({question: questionId});

updateStudent = (studentId, student) => studentModel.update({_id: studentId}, {$set: student});



module.exports = {
    truncateDatabase,
    populateDatabase,
    createStudent,
    deleteStudent,
    createQuestion,
    deleteQuestion,
    answerQuestion,
    deleteAnswer,

    findAllStudents, 
    findStudentById, 
    findAllQuestions,
    findQuestionById,
    findAllAnswers,
    findAnswerById,
    findAnswersByStudent,
    findAnswersByQuestion,

    updateStudent
};