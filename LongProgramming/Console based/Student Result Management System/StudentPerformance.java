class StudentPerformance {
    int tamil;
    int eng;
    int maths;
    int social;
    int science;

    public StudentPerformance(int tamil, int eng, int maths, int social, int science) {
        this.tamil = tamil;
        this.eng = eng;
        this.maths = maths;
        this.social = social;
        this.science = science;
    }
    public void showMarks(){

        System.out.println("Tamil: "+tamil);
        System.out.println("Eng: "+eng);
        System.out.println("Maths: "+maths);
        System.out.println("Social: "+social);
        System.out.println("Science: "+science);

    }

}
