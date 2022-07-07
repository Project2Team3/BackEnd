let questionForm = document.getElementById("questionForm");
let questionSetupForm = document.getElementById("questionSetupForm");
let questionContainer = document.getElementById("questionContainer");
let timerObject = null;

let questions = [];
let counter = 0;

let timeLimit = 60;
let countDown = function () {
    timeLimit--;
    let timeLeft = document.getElementById("timeLeft");
    timeLeft.innerHTML = timeLimit;
    if (timeLimit === 0) {
        clearInterval(countDown);
    }
};

const showQuestionHandler = () => {
    if (timerObject) {
        clearInterval(timerObject);
        timerObject = null;
    }
    let questionText = document.getElementById("questionText");
    let questionOne = document.getElementById("questionOne");
    let questionTwo = document.getElementById("questionTwo");
    let questionThree = document.getElementById("questionThree");
    let questionFour = document.getElementById("questionFour");

    let options = [];
    if (questions[counter].type === "multiple") {
        options = [
            questions[counter].answer,
            ...questions[counter].incorrectAnswers,
        ];
        options = options.sort();
    } else {
        options = ["True", "False"];
    }
    questionText.innerHTML = questions[counter].question;

    let questionOneHTML = questionOne;
    let questionOneParent = questionOne.parentElement;
    questionOneParent.innerHTML = options[0];
    questionOneParent.appendChild(questionOneHTML);

    let questionTwoHTML = questionTwo;
    let questionTwoParent = questionTwo.parentElement;
    questionTwoParent.innerHTML = options[1];
    questionTwoParent.appendChild(questionTwoHTML);

    if (options.length == 2) {
        questionThree.parentElement.classList.add("inactive");
        questionFour.parentElement.classList.add("inactive");
    } else if (options.length == 3) {
        let questionThreeHTML = questionThree;
        let questionThreeParent = questionThree.parentElement;
        questionThreeParent.innerHTML = options[2];
        questionThreeParent.appendChild(questionThreeHTML);
        questionFour.parentElement.classList.add("inactive");
    } else if (options.length === 4) {
        let questionThreeHTML = questionThree;
        let questionThreeParent = questionThree.parentElement;
        questionThreeParent.innerHTML = options[2];
        questionThreeParent.appendChild(questionThreeHTML);

        let questionFourHTML = questionFour;
        let questionFourParent = questionFour.parentElement;
        questionFourParent.innerHTML = options[3];
        questionFourParent.appendChild(questionFourHTML);
    }
    timeLimit = 60;
    timerObject = setInterval(countDown, 1000);
    if (counter === questions.length - 1) {
        clearInterval(countDown);
    }
    counter++;
};

questionForm.onsubmit = (e) => {
    e.preventDefault();
    console.log(e);
    let selected = e.target.querySelectorAll("input:checked");

    let answer = selected[0].parentElement.firstChild.textContent;
    let correct = questions[counter - 1].answer;
    console.log(answer, correct);
    if (answer === correct) {
        console.log("correct");
        selected[0].checked = false;
        showQuestionHandler();
    }
};

questionSetupForm.onsubmit = async (e) => {
    e.preventDefault();
    let amount = e.target[0].value;
    let category = e.target[1].value;
    let difficulty = e.target[2].value;
    let type = e.target[3].value;

    let data = await fetchQuestions(amount, category, difficulty, type);
    console.log(data);

    if (data.results) {
        for (let dataItem of data.results) {
            questions.push({
                answer: dataItem.correct_answer,
                incorrectAnswers: dataItem.incorrect_answers,
                question: dataItem.question,
                type: dataItem.type,
            });
        }
    }
    console.log(questions);

    questionSetupForm.classList.add("inactive");
    questionContainer.classList.remove("inactive");

    showQuestionHandler();
};

const fetchQuestions = async (amount, category, difficulty, type) => {
    let URL;
    if (category === "" && difficulty === "" && type === "") {
        URL = `https://opentdb.com/api.php?amount=${amount}`;
    } else if (difficulty === "" && type === "") {
        URL = `https://opentdb.com/api.php?amount=${amount}&category=${category}`;
    } else if (category === "" && difficulty === "") {
        URL = `https://opentdb.com/api.php?amount=${amount}&type=${type}`;
    } else if (category === "" && type === "") {
        URL = `https://opentdb.com/api.php?amount=${amount}&difficulty=${difficulty}`;
    } else if (category === "") {
        URL = `https://opentdb.com/api.php?amount=${amount}&difficulty=${difficulty}&type=${type}`;
    } else if (type === "") {
        URL = `https://opentdb.com/api.php?amount=${amount}&category=${category}&difficulty=${difficulty}`;
    } else if (difficulty === "") {
        URL = `https://opentdb.com/api.php?amount=${amount}&category=${category}&type=${type}`;
    } else {
        URL = `https://opentdb.com/api.php?amount=${amount}&category=${category}&difficulty=${difficulty}&type=${type}`;
    }
    return await fetch(URL).then((response) => response.json());
};
